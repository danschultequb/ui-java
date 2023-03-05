package qub;

/**
 * A fake draw rectangle operation performed by a {@link FakeUIPainter}.
 */
public class FakeDrawRectangleOperation extends FakeDrawOperation.Base<FakeDrawRectangleOperation>
{
    private final DrawRectangleOptions options;

    private FakeDrawRectangleOperation()
    {
        this.options = DrawRectangleOptions.create();
    }

    /**
     * Create a new {@link FakeDrawRectangleOperation}.
     */
    public static FakeDrawRectangleOperation create()
    {
        return new FakeDrawRectangleOperation();
    }

    /**
     * Set the {@link DrawRectangleOptions} of this {@link FakeDrawRectangleOperation}.
     * @param options The {@link DrawRectangleOptions} of this {@link FakeDrawRectangleOperation}.
     * @return This object for method chaining.
     */
    public FakeDrawRectangleOperation setOptions(DrawRectangleOptions options)
    {
        PreCondition.assertNotNull(options, "options");

        this.options.set(options);

        return this;
    }

    public FakeDrawRectangleOperation setLeftX(int leftX)
    {
        this.options.setLeftX(leftX);

        return this;
    }

    public FakeDrawRectangleOperation setTopY(int topY)
    {
        this.options.setTopY(topY);

        return this;
    }

    public FakeDrawRectangleOperation setSize(int width, int height)
    {
        this.options.setSize(width, height);

        return this;
    }

    public FakeDrawRectangleOperation setWidth(int width)
    {
        this.options.setWidth(width);

        return this;
    }

    public FakeDrawRectangleOperation setHeight(int height)
    {
        this.options.setHeight(height);

        return this;
    }

    public FakeDrawRectangleOperation setLineColor(Color lineColor)
    {
        this.options.setLineColor(lineColor);

        return this;
    }

    public FakeDrawRectangleOperation setFillColor(Color lineColor)
    {
        this.options.setFillColor(lineColor);

        return this;
    }

    @Override
    public boolean equals(FakeDrawOperation rhs)
    {
        return rhs instanceof FakeDrawRectangleOperation rhsOperation &&
            this.equals(rhsOperation);
    }

    public boolean equals(FakeDrawRectangleOperation rhs)
    {
        return super.equals(rhs) &&
            this.options.equals(rhs.options);
    }

    @Override
    public JSONObject toJson()
    {
        return super.toJson()
            .setObject(Base.optionsPropertyName, this.options.toJson());
    }
}
