//package qub;
//
///**
// * A {@link UIElement} that displays other {@link UIElement}s in a grid.
// */
//public interface UIGridLayout extends UIElement
//{
//    @Override
//    UIGridLayout setWidth(Distance width);
//
//    @Override
//    UIGridLayout setHeight(Distance height);
//
//    @Override
//    UIGridLayout setSize(Size2D size);
//
//    @Override
//    UIGridLayout setSize(Distance width, Distance height);
//
//    /**
//     * Add the provided {@link UIElement} to this {@link UIGridLayout}.
//     * @param x The x-coordinate in the {@link UIGridLayout} that the {@link UIElement} will be set to.
//     * @param y The y-coordinate in the {@link UIGridLayout} that the {@link UIElement} will be set to.
//     * @param element The {@link UIElement} to add to this {@link UIGridLayout}.
//     * @return This object for method chaining.
//     */
//    UIGridLayout setUIElement(int x, int y, UIElement element);
//
//    /**
//     * Get the {@link UIElement} at the provided row and column.
//     * @param x The x-coordinate in the {@link UIGridLayout} to get the {@link UIElement} from.
//     * @param y The y-coordinate in the {@link UIGridLayout} to get the {@link UIElement} from.
//     * @return The {@link UIElement} at the provided x and y coordinates.
//     */
//    Result<? extends UIElement> getUIElement(int x, int y);
//}
