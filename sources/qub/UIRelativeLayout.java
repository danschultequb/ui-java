//package qub;
//
///**
// * A {@link UIElement} that displays other {@link UIElement}s at specified positions relative to
// * this {@link UIElement}'s top-left corner.
// */
//public interface UIRelativeLayout extends UIElement
//{
//    @Override
//    UIRelativeLayout setWidth(Distance width);
//
//    @Override
//    UIRelativeLayout setHeight(Distance height);
//
//    @Override
//    UIRelativeLayout setSize(Size2D size);
//
//    @Override
//    UIRelativeLayout setSize(Distance width, Distance height);
//
//    /**
//     * Add the provided {@link UIElement} to this {@link UIPositionLayout}.
//     * @param topLeft The position relative to this {@link UIPositionLayout}'s top-left corner that
//     *                the provided {@link UIElement} will be positioned at.
//     * @param uiElement The {@link UIElement} to add to this {@link UIPositionLayout}.
//     * @return This object for method chaining.
//     */
//    default UIRelativeLayout add(Point2D topLeft, UIElement uiElement)
//    {
//        PreCondition.assertNotNull(topLeft, "topLeft");
//
//        return this.add(topLeft.getX(), topLeft.getY(), uiElement);
//    }
//
//    /**
//     * Add the provided {@link UIElement} to this {@link UIPositionLayout}.
//     * @param left The distance between this {@link UIPositionLayout}'s left edge and the provided
//     *             {@link UIElement}'s left edge.
//     * @param top The distance between this {@link UIPositionLayout}'s top edge and the provided
//     *            {@link UIElement}'s top edge.
//     * @param uiElement The {@link UIElement} to add to this {@link UIPositionLayout}.
//     * @return This object for method chaining.
//     */
//    UIRelativeLayout add(Distance left, Distance top, UIElement uiElement);
//}
