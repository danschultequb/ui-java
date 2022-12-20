//package qub;
//
///**
// * An editable text box element within a UI.
// */
//public interface UITextBox extends UIElement
//{
//    @Override
//    UITextBox setWidth(Distance width);
//
//    @Override
//    UITextBox setHeight(Distance height);
//
//    @Override
//    UITextBox setSize(Size2D size);
//
//    @Override
//    UITextBox setSize(Distance width, Distance height);
//
//    /**
//     * Get the text of this {@link UITextBox}.
//     * @return The text of this {@link UITextBox}.
//     */
//    String getText();
//
//    /**
//     * Set the text of this {@link UITextBox}.
//     * @param text The text of this {@link UITextBox}.
//     * @return This object for method chaining.
//     */
//    UITextBox setText(String text);
//
//    /**
//     * Register the provided callback to be invoked when this {@link UITextBox}'s text is changed.
//     * @param callback The callback to invoke when this {@link UITextBox}'s text is changed.
//     * @return A {@link Disposable} that can be disposed to unregister the provided callback.
//     */
//    default Disposable onTextChanged(Action0 callback)
//    {
//        PreCondition.assertNotNull(callback, "callback");
//
//        return this.onTextChanged((String newText) -> callback.run());
//    }
//
//    /**
//     * Register the provided callback to be invoked when this {@link UITextBox}'s text is changed.
//     * @param callback The callback to invoke when this {@link UITextBox}'s text is changed.
//     * @return A {@link Disposable} that can be disposed to unregister the provided callback.
//     */
//    Disposable onTextChanged(Action1<String> callback);
//
//    /**
//     * Get the size of the font used by this {@link UITextBox}.
//     * @return The size of the font used by this {@link UITextBox}.
//     */
//    Distance getFontSize();
//
//    /**
//     * Set the size of the font used by this {@link UITextBox}.
//     * @param fontSize The size of the font used by this {@link UITextBox}.
//     * @return This object for method chaining.
//     */
//    UITextBox setFontSize(Distance fontSize);
//}
