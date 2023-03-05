package qub;

/**
 * A fake draw text operation performed by a {@link FakeUIPainter}.
 */
public class FakeDrawTextOperation extends FakeDrawOperation.Base<FakeDrawTextOperation>
{
    private final DrawTextOptions options;

    private FakeDrawTextOperation()
    {
        this.options = DrawTextOptions.create();
    }

    /**
     * Create a new {@link FakeDrawTextOperation}.
     */
    public static FakeDrawTextOperation create()
    {
        return new FakeDrawTextOperation();
    }

    /**
     * Set the {@link DrawTextOptions} of this {@link FakeDrawTextOperation}.
     * @param options The {@link DrawTextOptions} of this {@link FakeDrawTextOperation}.
     * @return This object for method chaining.
     */
    public FakeDrawTextOperation setOptions(DrawTextOptions options)
    {
        PreCondition.assertNotNull(options, "options");

        this.options.set(options);

        return this;
    }

    public FakeDrawTextOperation setLineColor(Color lineColor)
    {
        this.options.setLineColor(lineColor);

        return this;
    }

    public FakeDrawTextOperation setLeftX(int leftX)
    {
        this.options.setLeftX(leftX);

        return this;
    }

    public FakeDrawTextOperation setCenterX(int centerX)
    {
        this.options.setCenterX(centerX);

        return this;
    }

    public FakeDrawTextOperation setRightX(int rightX)
    {
        this.options.setRightX(rightX);

        return this;
    }

    public FakeDrawTextOperation setTopY(int topY)
    {
        this.options.setTopY(topY);

        return this;
    }

    public FakeDrawTextOperation setCenterY(int centerY)
    {
        this.options.setCenterY(centerY);

        return this;
    }

    public FakeDrawTextOperation setBaselineY(int baselineY)
    {
        this.options.setBaselineY(baselineY);

        return this;
    }

    public FakeDrawTextOperation setBottomY(int bottomY)
    {
        this.options.setBottomY(bottomY);

        return this;
    }

    public FakeDrawTextOperation setText(String text)
    {
        this.options.setText(text);

        return this;
    }

    @Override
    public boolean equals(FakeDrawOperation rhs)
    {
        return rhs instanceof FakeDrawTextOperation rhsOperation &&
            this.equals(rhsOperation);
    }

    public boolean equals(FakeDrawTextOperation rhs)
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
