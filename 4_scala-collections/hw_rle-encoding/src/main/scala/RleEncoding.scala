import scala.io.StdIn

object RleEncodingApp extends App {

  println("Started")

  val rleEncoding = new RleEncoding

  println(rleEncoding.encode("ABCDE"))

  println(rleEncoding.encode("AABBCCDD"))
  println(rleEncoding.encode("XAABBCCDD"))

  println(rleEncoding.encode("AAAABBBCCXYZDDDDEEEFFFAAAAAABBBBBBBBBBBBBBBBBBBBBBBBBBBBB"))
  println(rleEncoding.encode("AAAABBBCCXYZDDDDEEEFFFAAAAAABBBBBBBBBBBBBBBBBBBBBBBBBBBBBX"))

//  println(rleEncoding.encode("AAAABBBCCXYZDDDDEEEFFFAAAAAABBBBBBBBBBBBBBBBBBBBBBBBBBBBBX" * 1_000_000))

  StdIn.readLine()
}

trait Block {
  def length: Int
}

case class UncompressedBlock(length: Int, data: Seq[Char]) extends Block
case class CompressedBlock(length: Int, data: Char) extends Block

class RleEncoding {

  def encode(str: String): Seq[Block] = {
    val (prev, block, result) =
      str.toCharArray.foldLeft((None: Option[Char], None: Option[Block], Seq.empty[Block])) {
        case ((None, _, result), char) =>
          (Some(char), None, result)

        case ((Some(prev), None, result), char) if prev == char =>
          (Some(char), Some(CompressedBlock(1, prev)), result)

        case ((Some(prev), None, result), char) =>
          (Some(char), Some(UncompressedBlock(1, Seq(prev))), result)

        case ((Some(prev), Some(block@CompressedBlock(_, _)), result), char) if prev == char =>
          (Some(char), Some(CompressedBlock(block.length + 1, block.data)), result)

        case ((Some(_), Some(block@CompressedBlock(_, _)), result), char) =>
          (Some(char), None, result :+ CompressedBlock(block.length + 1, block.data))

        case ((Some(prev), Some(block@UncompressedBlock(_, _)), result), char) if prev != char =>
          (Some(char), Some(UncompressedBlock(block.length + 1, block.data :+ prev)), result)

        case ((Some(prev), Some(block@UncompressedBlock(_, _)), result), char) =>
          (Some(char), Some(CompressedBlock(1, prev)), result :+ block)
      }

    val tail = (block, prev) match {
      case (Some(block@UncompressedBlock(_, _)), Some(prev)) => Seq(UncompressedBlock(block.length + 1, block.data :+ prev))
      case (Some(block@CompressedBlock(_, data)), _) => Seq(CompressedBlock(block.length + 1, data))
      case (None, Some(prev)) => Seq(UncompressedBlock(1, Seq(prev)))
      case (None, None) => Seq.empty[Block]
    }

    result ++ tail
  }
}
