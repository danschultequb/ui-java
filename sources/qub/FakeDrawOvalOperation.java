package qub;

/**
 * A fake draw oval operation performed by a {@link FakeUIPainter}.
 */
public class FakeDrawOvalOperation extends FakeDrawOperation.Base<FakeDrawOvalOperation>
{
    private final DrawOvalOptions options;

    private FakeDrawOvalOperation()
    {
        this.options = DrawOvalOptions.create();
    }

    /**
     * Create a new {@link FakeDrawOvalOperation}.
     */
    public static FakeDrawOvalOperation create()
    {
        return new FakeDrawOvalOperation();
    }

    /**
     * Set the {@link DrawOvalOptions} of this {@link FakeDrawOvalOperation}.
     * @param options The {@link DrawOvalOptions} of this {@link FakeDrawOvalOperation}.
     * @return This object for method chaining.
     */
    public FakeDrawOvalOperation setOptions(DrawOvalOptions options)
    {
        PreCondition.assertNotNull(options, "options");

        this.options.set(options);

        return this;
    }

    public FakeDrawOvalOperation setLeftX(int leftX)
    {
        this.options.setLeftX(leftX);

        return this;
    }

    public FakeDrawOvalOperation setTopY(int topY)
    {
        this.options.setTopY(topY);

        return this;
    }

    public FakeDrawOvalOperation setWidth(int width)
    {
        this.options.setWidth(width);

        return this;
    }

    public FakeDrawOvalOperation setHeight(int height)
    {
        this.options.setHeight(height);

        return this;
    }

    public FakeDrawOvalOperation setLineColor(Color lineColor)
    {
        this.options.setLineColor(lineColor);

        return this;
    }

    public FakeDrawOvalOperation setFillColor(Color fillColor)
    {
        this.options.setFillColor(fillColor);

        return this;
    }

    @Override
    public boolean equals(FakeDrawOperation rhs)
    {
        return rhs instanceof FakeDrawOvalOperation rhsOperation &&
            this.equals(rhsOperation);
    }

    public boolean equals(FakeDrawOvalOperation rhs)
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
