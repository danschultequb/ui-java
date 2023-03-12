package qub;

public class JavaBufferedImage extends java.awt.image.BufferedImage
{
    private JavaBufferedImage(int width, int height)
    {
        super(width, height, java.awt.image.BufferedImage.TYPE_INT_ARGB);
    }

    public static JavaBufferedImage create(int width, int height)
    {
        PreCondition.assertGreaterThanOrEqualTo(width, 1, "width");
        PreCondition.assertGreaterThanOrEqualTo(height, 1, "height");

        return new JavaBufferedImage(width, height);
    }

    @Override
    public java.awt.Graphics2D getGraphics()
    {
        PreCondition.assertInstanceOf(super.getGraphics(), java.awt.Graphics2D.class, "super.getGraphics()");

        return (java.awt.Graphics2D)super.getGraphics();
    }

    public int[] getPixels()
    {
        final int width = this.getWidth();
        return this.getRGB(0, 0, width, this.getHeight(), null, 0, width);
    }

    public JavaBufferedImage setPixels(int... rgb)
    {
        PreCondition.assertNotNull(rgb, "rgb");
        PreCondition.assertEqual(this.getWidth() * this.getHeight(), rgb.length, "rgb.length");

        final int width = this.getWidth();
        this.setRGB(0, 0, width, this.getHeight(), rgb, 0, width);

        return this;
    }
}
