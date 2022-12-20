//package qub;
//
///**
// * A {@link UIElement} that is contained by another {@link UIElement}
// */
//public interface UIElementChild extends UIElement
//{
//    /**
//     * Get the {@link UIElementParent} that contains this {@link UIElementChild}.
//     * @return The {@link UIElementParent} that contains this {@link UIElementChild}.
//     */
//    UIElementParent getParent();
//
//    /**
//     * Set the {@link UIElementParent} that contains this {@link UIElementChild}.
//     * @param parent The {@link UIElementParent} that contains this {@link UIElementChild}.
//     * @return This object for method chaining.
//     */
//    UIElementChild setParent(UIElementParent parent);
//
//    /**
//     * Register the provided callback to be invoked when this {@link UIElementChild}'s parent
//     * changes.
//     * @param callback The callback to register.
//     * @return A {@link Disposable} that can be disposed to unregister the callback.
//     */
//    default Disposable onParentChanged(Action0 callback)
//    {
//        PreCondition.assertNotNull(callback, "callback");
//
//        return this.onParentChanged((UIElementParent previousParent, UIElementParent newParent) ->
//        {
//            callback.run();
//        });
//    }
//
//    /**
//     * Register the provided callback to be invoked when this {@link UIElementChild}'s parent
//     * changes.
//     * @param callback The callback to register.
//     * @return A {@link Disposable} that can be disposed to unregister the callback.
//     */
//    default Disposable onParentChanged(Action1<UIElementParent> callback)
//    {
//        PreCondition.assertNotNull(callback, "callback");
//
//        return this.onParentChanged((UIElementParent previousParent, UIElementParent newParent) ->
//        {
//            callback.run(newParent);
//        });
//    }
//
//    /**
//     * Register the provided callback to be invoked when this {@link UIElementChild}'s parent
//     * changes.
//     * @param callback The callback to register.
//     * @return A {@link Disposable} that can be disposed to unregister the callback.
//     */
//    Disposable onParentChanged(Action2<UIElementParent,UIElementParent> callback);
//}
