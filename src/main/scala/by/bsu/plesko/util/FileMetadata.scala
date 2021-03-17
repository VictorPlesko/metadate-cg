package by.bsu.plesko.util

import com.drew.imaging.ImageMetadataReader
import com.drew.metadata.Metadata
import com.drew.metadata.exif.ExifSubIFDDirectory

import java.io.File
import scala.collection.mutable.ListBuffer
import scala.util.{Failure, Success, Try}

sealed trait ParametersFile {
  def getFileName: String
  def getImageSize: String
  def getResolution: String
  def getColorDepth: String
  def getCompression: String
}

final case class FileMetadata(file: File) extends ParametersFile {
  private val imageWidthTag: String = "Image Width"
  private val imageHeightTag: String = "Image Height"
  private val resolutionTag: String = "X Resolution"
  private val colorDepth1Tag: String = "Data Precision"
  private val colorDepth2Tag: String = "Bits Per Sample"
  private val compressionTag: String = "Compression"
  private val metadata: Metadata = ImageMetadataReader.readMetadata(file)

  override def getFileName: String = file.getName

  override def getImageSize: String = {
    val listBuffer = new ListBuffer[String]
    metadata.
      getDirectories.
      iterator().
      forEachRemaining(_.getTags.
        iterator().
        forEachRemaining(x =>
          if ((x.getTagName == imageWidthTag || x.getTagName == imageHeightTag) && listBuffer.length != 2) listBuffer += x.getDescription))
    if (listBuffer.length == 2) {
      listBuffer.head.split("\\s+")(0) + " x " + listBuffer.last.split("\\s+")(0)
    }
    else "-"
  }

  override def getResolution: String = {
    val stringBuilder = new StringBuilder()
    metadata.
      getDirectories.
      iterator().
      forEachRemaining(_.getTags.
        iterator().
        forEachRemaining(x => if (x.getTagName == resolutionTag && stringBuilder.isEmpty) stringBuilder.append(x.getDescription)))
    if (stringBuilder.isEmpty) "-" else stringBuilder.toString
  }

  override def getColorDepth: String = {
    val stringBuilder = new StringBuilder()
    metadata.
      getDirectories.
      iterator().
      forEachRemaining(_.getTags.
        iterator().
        forEachRemaining(x => if ((x.getTagName == colorDepth2Tag || x.getTagName == colorDepth1Tag) && stringBuilder.isEmpty)
          stringBuilder.append(x.getDescription)))
    if (stringBuilder.isEmpty) "-" else stringBuilder.toString.split("\\s+")(0)
  }

  override def getCompression: String = {
    val stringBuilder = new StringBuilder()
    metadata.
      getDirectories.
      iterator().
      forEachRemaining(_.getTags.
        iterator().
        forEachRemaining(x => if (x.getTagName == compressionTag && stringBuilder.isEmpty) stringBuilder.append(x.getDescription)))
    if (stringBuilder.isEmpty) "-" else stringBuilder.toString
  }
}

/*object Example {
  def prob(file: File) = {
    val mt = ImageMetadataReader.readMetadata(file)
    mt.getDirectories.iterator().forEachRemaining(_.getTags.iterator().forEachRemaining(println))
  }
}*/
