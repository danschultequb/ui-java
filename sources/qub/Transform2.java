package qub;

/**
 * An object that can apply transforms against 2-dimensional objects.
 */
public interface Transform2
{
    /**
     * Create a new {@link MutableTransform2}.
     */
    public static MutableTransform2 create()
    {
        return MutableTransform2.create();
    }

    /**
     * Create a new {@link MutableTransform2} from the provided {@link Transform2}.
     * @param toCopy The {@link Transform2} to copy.
     */
    public static MutableTransform2 create(Transform2 toCopy)
    {
        return MutableTransform2.create(toCopy);
    }

    /**
     * Apply this {@link Transform2} to the provided {@link Point2Integer} and return the result.
     * @param point The {@link Point2Integer} to transform.
     */
    public Point2Integer apply(Point2Integer point);

    /**
     * Apply the {@link Transform2} to the provided {@link MutablePoint2Integer} and assign the
     * result of the transform back to the provided {@link MutablePoint2Integer}.
     * @param point The {@link MutablePoint2Integer} to transform and assign the result to.
     */
    public void applyAssign(MutablePoint2Integer point);

    /**
     * Get whether the provided {@link Transform2} is equal to this {@link Transform2}.
     * @param rhs The {@link Transform2} to compare to this {@link Transform2}.
     */
    public boolean equals(Transform2 rhs);

    /**
     * Get a {@link String} that portrays this {@link Transform2} as a 3x3 matrix.
     */
    @Override
    public String toString();

    /**
     * Get a {@link JSONArray} that portrays this {@link Transform2} as a 3x3 matrix.
     */
    public JSONArray toJson();

    /**
     * Get whether the provided {@link Transform2} is equal to the provided {@link Object}.
     * @param lhs The {@link Transform2} to compare.
     * @param rhs The {@link Object} to compare.
     */
    public static boolean equals(Transform2 lhs, Object rhs)
    {
        PreCondition.assertNotNull(lhs, "lhs");

        return rhs instanceof Transform2 rhsTransform && lhs.equals(rhsTransform);
    }

    /**
     * Get a {@link String} that portrays the provided {@link Transform2} as a 3x3 matrix.
     */
    public static String toString(Transform2 transform)
    {
        PreCondition.assertNotNull(transform, "transform");

        return transform.toJson().toString();
    }

    /**
     * An abstract base class implementation of {@link Transform2} that contains common properties
     * and functions for all {@link Transform2} types.
     */
    public static abstract class Base implements Transform2
    {
        @Override
        public String toString()
        {
            return Transform2.toString(this);
        }

        @Override
        public boolean equals(Object rhs)
        {
            return Transform2.equals(this, rhs);
        }
    }
}
