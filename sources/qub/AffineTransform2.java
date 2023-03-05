package qub;

/**
 * A type that adapts a {@link java.awt.geom.AffineTransform} to a {@link MutableTransform2}.
 */
public class AffineTransform2 extends Transform2.Base implements MutableTransform2.Typed<AffineTransform2>
{
    private final java.awt.geom.AffineTransform affineTransform;

    private AffineTransform2(java.awt.geom.AffineTransform affineTransform)
    {
        PreCondition.assertNotNull(affineTransform, "affineTransform");

        this.affineTransform = affineTransform;
    }

    /**
     * Create a new {@link AffineTransform2}.
     */
    public static AffineTransform2 create()
    {
        return AffineTransform2.create(new java.awt.geom.AffineTransform());
    }

    /**
     * Create a new {@link AffineTransform2} from the provided {@link Transform2}.
     * @param toCopy The {@link Transform2} to copy.
     */
    public static AffineTransform2 create(Transform2 toCopy)
    {
        PreCondition.assertNotNull(toCopy, "toCopy");
        PreCondition.assertInstanceOf(toCopy, AffineTransform2.class, "toCopy");

        final AffineTransform2 toCopyAffine = (AffineTransform2)toCopy;
        return AffineTransform2.create(toCopyAffine.affineTransform);
    }

    public static AffineTransform2 create(java.awt.geom.AffineTransform affineTransform)
    {
        return new AffineTransform2(new java.awt.geom.AffineTransform(affineTransform));
    }

    public java.awt.geom.AffineTransform getAffineTransform()
    {
        return this.affineTransform;
    }

    public AffineTransform2 setAffineTransform(java.awt.geom.AffineTransform affineTransform)
    {
        PreCondition.assertNotNull(affineTransform, "affineTransform");

        this.affineTransform.setTransform(affineTransform);

        return this;
    }

    @Override
    public AffineTransform2 set(Transform2 toCopy)
    {
        PreCondition.assertNotNull(toCopy, "toCopy");
        PreCondition.assertInstanceOf(toCopy, AffineTransform2.class, "toCopy");

        final AffineTransform2 toCopyAffineTransform = (AffineTransform2)toCopy;
        this.affineTransform.setTransform(toCopyAffineTransform.affineTransform);

        return this;
    }

    @Override
    public AffineTransform2 translate(int x, int y)
    {
        this.affineTransform.translate(x, y);

        return this;
    }

    @Override
    public Point2Integer apply(Point2Integer point)
    {
        PreCondition.assertNotNull(point, "point");

        final double[] points = new double[4];
        points[0] = point.getXAsInt();
        points[1] = point.getYAsInt();
        this.affineTransform.transform(points, 0, points, 2, 1);

        final double resultX = points[2];
        final double resultY = points[3];
        final Point2Integer result = Point2.create((int)resultX, (int)resultY);

        PostCondition.assertNotNull(result, "result");

        return result;
    }

    @Override
    public void applyAssign(MutablePoint2Integer point)
    {
        PreCondition.assertNotNull(point, "point");

        final double[] points = new double[4];
        points[0] = point.getXAsInt();
        points[1] = point.getYAsInt();
        this.affineTransform.transform(points, 0, points, 2, 1);

        final double resultX = points[2];
        final double resultY = points[3];
        point.set((int)resultX, (int)resultY);
    }

    @Override
    public boolean equals(Transform2 rhs)
    {
        return rhs instanceof AffineTransform2 rhsTransform && this.equals(rhsTransform);
    }

    /**
     * Get whether the provided {@link AffineTransform2} is equal to this {@link AffineTransform2}.
     * @param rhs The {@link AffineTransform2} to compare to this {@link AffineTransform2}.
     */
    public boolean equals(AffineTransform2 rhs)
    {
        return rhs != null &&
            this.affineTransform.equals(rhs.affineTransform);
    }

    @Override
    public JSONArray toJson()
    {
        return JSONArray.create(
            JSONArray.create()
                .addNumber(this.affineTransform.getScaleX())
                .addNumber(this.affineTransform.getShearX())
                .addNumber(this.affineTransform.getTranslateX()),
            JSONArray.create()
                .addNumber(this.affineTransform.getShearY())
                .addNumber(this.affineTransform.getScaleY())
                .addNumber(this.affineTransform.getTranslateY()),
            JSONArray.create()
                .addNumber(0.0)
                .addNumber(0.0)
                .addNumber(1.0));
    }
}
