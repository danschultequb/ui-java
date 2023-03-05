package qub;

/**
 * A fake draw operation performed by a {@link FakeUIPainter}.
 */
public interface FakeDrawOperation
{
    /**
     * Get the {@link Transform2} that was applied when this {@link FakeDrawOperation} was
     * performed.
     */
    public Transform2 getTransform();

    /**
     * Set the {@link Transform2} that was applied when this {@link FakeDrawOperation} was
     * performed.
     * @param transform The {@link Transform2} that was applied when this {@link FakeDrawOperation}
     *                  was performed.
     * @return This object for method chaining.
     */
    public FakeDrawOperation setTransform(Transform2 transform);

    /**
     * Translate this {@link FakeDrawOperation}'s {@link Transform2} by the provided x-value.
     * @param x The x-value to translate this {@link FakeDrawOperation}'s {@link Transform2}.
     * @return This object for method chaining.
     */
    public default FakeDrawOperation translateX(int x)
    {
        return this.translate(x, 0);
    }

    /**
     * Translate this {@link FakeDrawOperation}'s {@link Transform2} by the provided y-value.
     * @param y The y-value to translate this {@link FakeDrawOperation}'s {@link Transform2}.
     * @return This object for method chaining.
     */
    public default FakeDrawOperation translateY(int y)
    {
        return this.translate(0, y);
    }

    /**
     * Translate this {@link FakeDrawOperation}'s {@link Transform2} by the provided x- and
     * y-values.
     * @param x The x-value to translate this {@link FakeDrawOperation}'s {@link Transform2}.
     * @param y The y-value to translate this {@link FakeDrawOperation}'s {@link Transform2}.
     * @return This object for method chaining.
     */
    public default FakeDrawOperation translate(int x, int y)
    {
        final MutableTransform2 transform = Transform2.create(this.getTransform());
        transform.translate(x, y);
        return this.setTransform(transform);
    }

    /**
     * Get whether this {@link FakeDrawOperation} is equal to the provided {@link FakeDrawOperation}.
     * @param rhs The {@link FakeDrawOperation} to compare against this {@link FakeDrawOperation}.
     */
    public boolean equals(FakeDrawOperation rhs);

    /**
     * Get the JSON representation of this {@link FakeDrawOperation}.
     */
    public JSONObject toJson();

    /**
     * A version of a {@link FakeDrawOperation} that returns its own types from chainable methods.
     * @param <T> The actual type of the {@link FakeDrawOperation}.
     */
    public interface Typed<T extends FakeDrawOperation> extends FakeDrawOperation
    {
        @Override
        public T setTransform(Transform2 transform);

        @Override
        @SuppressWarnings("unchecked")
        public default T translateX(int x)
        {
            return (T)FakeDrawOperation.super.translateX(x);
        }

        @Override
        @SuppressWarnings("unchecked")
        public default T translateY(int y)
        {
            return (T)FakeDrawOperation.super.translateY(y);
        }

        @Override
        @SuppressWarnings("unchecked")
        public default T translate(int x, int y)
        {
            return (T)FakeDrawOperation.super.translate(x, y);
        }
    }

    /**
     * An abstract base class implementation of {@link FakeDrawOperation} that contains common
     * properties and functions for all {@link FakeDrawOperation} types.
     */
    public abstract class Base<T extends FakeDrawOperation> implements FakeDrawOperation.Typed<T>
    {
        protected static final String optionsPropertyName = "options";
        private static final String typePropertyName = "type";
        private static final String transformPropertyName = "transform";

        private Transform2 transform;

        protected Base()
        {
            this.transform = Transform2.create();
        }

        @Override
        public Transform2 getTransform()
        {
            return this.transform;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T setTransform(Transform2 transform)
        {
            PreCondition.assertNotNull(transform, "transform");

            this.transform = transform;

            return (T)this;
        }

        @Override
        public boolean equals(Object rhs)
        {
            return rhs instanceof FakeDrawOperation rhsOperation &&
                this.equals(rhsOperation);
        }

        @Override
        public boolean equals(FakeDrawOperation rhs)
        {
            return rhs != null &&
                this.transform.equals(rhs.getTransform());
        }

        @Override
        public String toString()
        {
            return this.toJson().toString();
        }

        @Override
        public JSONObject toJson()
        {
            final JSONObject result = JSONObject.create()
                .setString(Base.typePropertyName, Types.getFullTypeName(this));
            if (this.transform != null)
            {
                result.setArray(Base.transformPropertyName, this.transform.toJson());
            }

            PostCondition.assertNotNull(result, "result");

            return result;
        }
    }
}
