package qub;

public class MutableSizeChange implements SizeChange
{
    private int previousWidth;
    private int previousHeight;
    private int newWidth;
    private int newHeight;

    private MutableSizeChange()
    {
    }

    public static MutableSizeChange create()
    {
        return new MutableSizeChange();
    }

    public MutableSizeChange setPreviousWidth(int previousWidth)
    {
        PreCondition.assertGreaterThanOrEqualTo(previousWidth, 0, "previousWidth");

        this.previousWidth = previousWidth;

        return this;
    }

    @Override
    public int getPreviousWidth()
    {
        return this.previousWidth;
    }

    public MutableSizeChange setPreviousHeight(int previousHeight)
    {
        PreCondition.assertGreaterThanOrEqualTo(previousHeight, 0, "previousHeight");

        this.previousHeight = previousHeight;

        return this;
    }

    @Override
    public int getPreviousHeight()
    {
        return this.previousHeight;
    }

    public MutableSizeChange setPreviousSize(Size2Integer previousSize)
    {
        PreCondition.assertNotNull(previousSize, "previousSize");

        this.setPreviousWidth(previousSize.getWidthAsInt());
        this.setPreviousHeight(previousSize.getHeightAsInt());

        return this;
    }

    public MutableSizeChange setPreviousSize(int previousWidth, int previousHeight)
    {
        this.setPreviousWidth(previousWidth);
        this.setPreviousHeight(previousHeight);

        return this;
    }

    @Override
    public Size2Integer getPreviousSize()
    {
        return Size2.create(this.previousWidth, this.previousHeight);
    }

    public MutableSizeChange setNewWidth(int newWidth)
    {
        PreCondition.assertGreaterThanOrEqualTo(newWidth, 0, "newWidth");

        this.newWidth = newWidth;

        return this;
    }

    @Override
    public int getNewWidth()
    {
        return this.newWidth;
    }

    public MutableSizeChange setNewHeight(int newHeight)
    {
        PreCondition.assertGreaterThanOrEqualTo(newHeight, 0, "newHeight");

        this.newHeight = newHeight;

        return this;
    }

    @Override
    public int getNewHeight()
    {
        return this.newHeight;
    }

    public MutableSizeChange setNewSize(int newWidth, int newHeight)
    {
        this.setNewWidth(newWidth);
        this.setNewHeight(newHeight);

        return this;
    }

    public MutableSizeChange setNewSize(Size2Integer newSize)
    {
        PreCondition.assertNotNull(newSize, "newSize");

        this.setNewWidth(newSize.getWidthAsInt());
        this.setNewHeight(newSize.getHeightAsInt());

        return this;
    }

    @Override
    public Size2Integer getNewSize()
    {
        return Size2.create(this.newWidth, this.newHeight);
    }
}
