package qub;

/**
 * A {@link UIPainter} implementation that wraps around a {@link java.awt.Graphics} object.
 */
public class SwingUIPainter implements UIPainter.Typed<SwingUIPainter>
{
    private final java.awt.Graphics2D graphics;

    private java.awt.Color fillColor;
    private java.awt.Color lineColor;

    private SwingUIPainter(java.awt.Graphics2D graphics)
    {
        PreCondition.assertNotNull(graphics, "graphics");

        this.graphics = graphics;

        this.fillColor = JavaAwtColors.convertColorToJavaAwtColor(Color.transparent);
        this.lineColor = this.graphics.getColor();
    }

    public static SwingUIPainter create(java.awt.Graphics2D graphics)
    {
        return new SwingUIPainter(graphics);
    }

    public static SwingUIPainter create(java.awt.image.BufferedImage image)
    {
        PreCondition.assertNotNull(image, "image");
        PreCondition.assertInstanceOf(image.getGraphics(), java.awt.Graphics2D.class, "image.getGraphics()");

        return SwingUIPainter.create((java.awt.Graphics2D)image.getGraphics());
    }

    @Override
    public Color getFillColor()
    {
        return JavaAwtColors.convertJavaAwtColorToColor(this.fillColor);
    }

    @Override
    public Color getLineColor()
    {
        return JavaAwtColors.convertJavaAwtColorToColor(this.lineColor);
    }

    @Override
    public SwingUIPainter setFillColor(Color fillColor)
    {
        PreCondition.assertNotNull(fillColor, "fillColor");

        this.fillColor = JavaAwtColors.convertColorToJavaAwtColor(fillColor);

        return this;
    }

    @Override
    public SwingUIPainter setLineColor(Color lineColor)
    {
        PreCondition.assertNotNull(lineColor, "lineColor");

        this.lineColor = JavaAwtColors.convertColorToJavaAwtColor(lineColor);

        return this;
    }

    private Disposable setColors(Color lineColor, Color fillColor)
    {
        final Color lineColorBackup = (lineColor == null ? null : this.getLineColor());
        if (lineColor != null)
        {
            this.setLineColor(lineColor);
        }

        final Color fillColorBackup = (fillColor == null ? null : this.getFillColor());
        if (fillColor != null)
        {
            this.setFillColor(fillColor);
        }

        return Disposable.create(() ->
        {
            if (lineColorBackup != null)
            {
                this.setLineColor(lineColorBackup);
            }

            if (fillColorBackup != null)
            {
                this.setFillColor(fillColor);
            }
        });
    }

    @Override
    public SwingUIPainter drawLine(int startX, int startY, int endX, int endY)
    {
        this.graphics.setColor(this.lineColor);
        this.graphics.drawLine(startX, startY, endX, endY);

        return this;
    }

    @Override
    public SwingUIPainter drawLine(DrawLineOptions options)
    {
        PreCondition.assertNotNull(options, "options");

        try (final Disposable colorState = this.setColors(options.getLineColor(), null))
        {
            return this.drawLine(options.getStartX(), options.getStartY(), options.getEndX(), options.getEndY());
        }
    }

    @Override
    public SwingUIPainter drawOval(int leftX, int topY, int width, int height)
    {
        PreCondition.assertGreaterThanOrEqualTo(width, 0, "width");
        PreCondition.assertGreaterThanOrEqualTo(height, 0, "height");

        if (this.fillColor.getAlpha() != 0)
        {
            this.graphics.setColor(this.fillColor);
            this.graphics.fillOval(leftX, topY, width, height);
        }

        if (this.lineColor.getAlpha() != 0)
        {
            this.graphics.setColor(this.lineColor);
            this.graphics.drawOval(leftX, topY, width, height);
        }

        return this;
    }

    @Override
    public SwingUIPainter drawOval(DrawOvalOptions options)
    {
        PreCondition.assertNotNull(options, "options");

        try (final Disposable colorState = this.setColors(options.getLineColor(), options.getFillColor()))
        {
            return this.drawOval(options.getLeftX(), options.getTopY(), options.getWidth(), options.getHeight());
        }
    }

    @Override
    public SwingUIPainter drawRectangle(int leftX, int topY, int width, int height)
    {
        PreCondition.assertGreaterThanOrEqualTo(width, 0, "width");
        PreCondition.assertGreaterThanOrEqualTo(height, 0, "height");

        if (this.fillColor.getAlpha() != 0)
        {
            this.graphics.setColor(this.fillColor);
            this.graphics.fillRect(leftX, topY, width, height);
        }

        if (this.lineColor.getAlpha() != 0)
        {
            this.graphics.setColor(this.lineColor);
            this.graphics.drawRect(leftX, topY, width, height);
        }

        return this;
    }

    @Override
    public SwingUIPainter drawRectangle(DrawRectangleOptions options)
    {
        PreCondition.assertNotNull(options, "options");

        final Color backupLineColor = this.getLineColor();
        final Color backupFillColor = this.getFillColor();
        final Color optionsLineColor = options.getLineColor();
        final Color optionsFillColor = options.getFillColor();
        try
        {
            if (optionsLineColor != null)
            {
                this.setLineColor(optionsLineColor);
            }
            if (optionsFillColor != null)
            {
                this.setFillColor(optionsFillColor);
            }

            this.drawRectangle(options.getLeftX(), options.getTopY(), options.getWidth(), options.getHeight());
        }
        finally
        {
            if (optionsLineColor != null)
            {
                this.setLineColor(backupLineColor);
            }
            if (optionsFillColor != null)
            {
                this.setFillColor(backupFillColor);
            }
        }

        return this;
    }

    @Override
    public SwingUIPainter drawText(int leftX, int baselineY, String text)
    {
        PreCondition.assertNotNull(text, "text");

        this.graphics.setColor(this.lineColor);
        this.graphics.drawString(text, leftX, baselineY);

        return this;
    }

    @Override
    public SwingUIPainter drawText(DrawTextOptions options)
    {
        PreCondition.assertNotNull(options, "options");
        PreCondition.assertTrue(options.hasX(), "options.hasX()");
        PreCondition.assertTrue(options.hasY(), "options.hasY()");
        PreCondition.assertNotNull(options.getText(), "options.getText()");

        final String text = options.getText();

        Integer leftX = options.getLeftX();
        Integer baselineY = options.getBaselineY();
        if (leftX == null || baselineY == null)
        {
            final TextMeasurements textMeasurements = this.getTextMeasurements(text);

            if (leftX == null)
            {
                final int textWidth = textMeasurements.getWidth();

                final Integer centerX = options.getCenterX();
                if (centerX != null)
                {
                    leftX = centerX - (textWidth / 2);
                }
                else
                {
                    leftX = options.getRightX() - textWidth;
                }
            }

            if (baselineY == null)
            {
                final Integer topY = options.getTopY();
                if (topY != null)
                {
                    baselineY = topY + textMeasurements.getAscent();
                }
                else
                {
                    final Integer bottomY = options.getBottomY();
                    if (bottomY != null)
                    {
                        baselineY = bottomY - textMeasurements.getDescent();
                    }
                    else
                    {
                        final int centerY = options.getCenterY();
                        final int ascent = textMeasurements.getAscent();
                        final int height = ascent + textMeasurements.getDescent();
                        baselineY = centerY - (height / 2) + ascent;
                    }
                }
            }
        }

        return this.drawText(leftX, baselineY, text);
    }

    @Override
    public SwingUIPainter translate(int x, int y)
    {
        this.graphics.translate(x, y);

        return this;
    }

    @Override
    public SwingUIPainter setTransform(Transform2 transform)
    {
        PreCondition.assertNotNull(transform, "transform");
        PreCondition.assertInstanceOf(transform, AffineTransform2.class, "transform");

        final AffineTransform2 affineTransform = (AffineTransform2)transform;
        this.graphics.setTransform(affineTransform.getAffineTransform());

        return this;
    }

    @Override
    public Size2Integer getClip()
    {
        final java.awt.Rectangle clipBounds = this.graphics.getClipBounds();
        return clipBounds == null ? null : Size2.create(clipBounds.width, clipBounds.height);
    }

    @Override
    public SwingUIPainter setClip(UIPainterSetClipSizeOptions options)
    {
        PreCondition.assertNotNull(options, "options");

        if (options.getResetClip())
        {
            this.graphics.setClip(0, 0, options.getWidth(), options.getHeight());
        }
        else
        {
            this.graphics.clipRect(0, 0, options.getWidth(), options.getHeight());
        }
        return this;
    }

    @Override
    public Transform2 getTransform()
    {
        return AffineTransform2.create(this.graphics.getTransform());
    }

    @Override
    public TextMeasurements getTextMeasurements(String text)
    {
        PreCondition.assertNotNull(text, "text");

        return TextMeasurements.create(text, this.graphics.getFontMetrics());
    }
}
