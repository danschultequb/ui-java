package qub;

/**
 * A fake draw line operation performed by a {@link FakeUIPainter}.
 */
public class FakeDrawLineOperation extends FakeDrawOperation.Base<FakeDrawLineOperation>
{
    private DrawLineOptions options;

    private FakeDrawLineOperation()
    {
        this.options = DrawLineOptions.create();
    }

    /**
     * Create a new {@link FakeDrawLineOperation}.
     */
    public static FakeDrawLineOperation create()
    {
        return new FakeDrawLineOperation();
    }

    /**
     * Set the {@link DrawLineOptions} of this {@link FakeDrawLineOperation}.
     * @param options The {@link DrawLineOptions} of this {@link FakeDrawLineOperation}.
     * @return This object for method chaining.
     */
    public FakeDrawLineOperation setOptions(DrawLineOptions options)
    {
        PreCondition.assertNotNull(options, "options");

        this.options = options;

        return this;
    }

    public FakeDrawLineOperation setStartX(int startX)
    {
        this.options.setStartX(startX);

        return this;
    }

    public FakeDrawLineOperation setStartY(int startY)
    {
        this.options.setStartY(startY);

        return this;
    }

    public FakeDrawLineOperation setStart(int startX, int startY)
    {
        this.options.setStart(startX, startY);

        return this;
    }

    public FakeDrawLineOperation setStart(Point2Integer start)
    {
        this.options.setStart(start);

        return this;
    }

    public FakeDrawLineOperation setEndX(int endX)
    {
        this.options.setEndX(endX);

        return this;
    }

    public FakeDrawLineOperation setEndY(int endY)
    {
        this.options.setEndY(endY);

        return this;
    }

    public FakeDrawLineOperation setEnd(Point2Integer end)
    {
        this.options.setEnd(end);

        return this;
    }

    public FakeDrawLineOperation setEnd(int endX, int endY)
    {
        this.options.setEnd(endX, endY);

        return this;
    }

    public FakeDrawLineOperation setLineColor(Color lineColor)
    {
        this.options.setLineColor(lineColor);

        return this;
    }

    @Override
    public boolean equals(FakeDrawOperation rhs)
    {
        return rhs instanceof FakeDrawLineOperation rhsOperation &&
            this.equals(rhsOperation);
    }

    public boolean equals(FakeDrawLineOperation rhs)
    {
        return super.equals(rhs) &&
            this.options.equals(rhs.options);
    }

    @Override
    public JSONObject toJson()
    {
        final JSONObject result = super.toJson();
        if (this.options != null)
        {
            result.setObject(FakeDrawOperation.Base.optionsPropertyName, this.options.toJson());
        }

        PostCondition.assertNotNull(result, "result");

        return result;
    }
}
