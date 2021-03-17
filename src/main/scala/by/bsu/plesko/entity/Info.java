package by.bsu.plesko.entity;

import java.util.Objects;

public class Info {
    private String fileName;
    private String imageSize;
    private String resolution;
    private String colorDepth;
    private String compression;

    public Info(String fileName, String imageSize, String resolution, String colorDepth, String compression) {
        this.fileName = fileName;
        this.imageSize = imageSize;
        this.resolution = resolution;
        this.colorDepth = colorDepth;
        this.compression = compression;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getImageSize() {
        return imageSize;
    }

    public void setImageSize(String imageSize) {
        this.imageSize = imageSize;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getColorDepth() {
        return colorDepth;
    }

    public void setColorDepth(String colorDepth) {
        this.colorDepth = colorDepth;
    }

    public String getCompression() {
        return compression;
    }

    public void setCompression(String compression) {
        this.compression = compression;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Info info = (Info) o;
        return Objects.equals(fileName, info.fileName) && Objects.equals(imageSize, info.imageSize) && Objects.equals(resolution, info.resolution) && Objects.equals(colorDepth, info.colorDepth) && Objects.equals(compression, info.compression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, imageSize, resolution, colorDepth, compression);
    }

    @Override
    public String toString() {
        return "Info2{" +
                "fileName='" + fileName + '\'' +
                ", imageSize='" + imageSize + '\'' +
                ", resolution='" + resolution + '\'' +
                ", colorDepth='" + colorDepth + '\'' +
                ", compression='" + compression + '\'' +
                '}';
    }
}
