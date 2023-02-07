package qub;

/**
 * An editable text box element within a {@link UI}.
 */
public interface UITextBox extends UIElement
{
    @Override
    public UITextBox setBackgroundColor(Color backgroundColor);

    @Override
    public UITextBox setWidth(int width);

    @Override
    public UITextBox setWidth(Distance width);

    @Override
    public UITextBox setHeight(int height);

    @Override
    public UITextBox setHeight(Distance height);

    @Override
    public UITextBox setSize(int width, int height);

    @Override
    public UITextBox setSize(Size2Integer size);

    @Override
    public UITextBox setDynamicSize(DynamicSize2Integer dynamicSize);

    /**
     * Get the number of characters in the text.
     */
    public int getTextLength();

    /**
     * Get the text of this {@link UITextBox}.
     */
    public String getText();

    /**
     * Set the text of this {@link UITextBox}.
     * @param text The text of this {@link UITextBox}.
     * @return This object for method chaining.
     */
    public UITextBox setText(String text);

    /**
     * Insert the provided text at the provided startIndex.
     * @param startIndex The index at which to insert the provided text.
     * @param text The text to insert.
     * @return This object for method chaining.
     */
    public UITextBox insertText(int startIndex, String text);

    /**
     * Remove the text from the provided range.
     * @param startIndex The index at which to start removing characters.
     * @param length The number of characters to remove.
     * @return The text that was removed.
     */
    public UITextBox removeText(int startIndex, int length);

    /**
     * Register the provided {@link Action0} to be invoked when this {@link UITextBox}'s text is
     * changed.
     * @param callback The {@link Action0} to invoke when this {@link UITextBox}'s text is changed.
     * @return A {@link Disposable} that can be disposed to unregister the provided {@link Action0}.
     */
    public default Disposable onTextChanged(Action0 callback)
    {
        PreCondition.assertNotNull(callback, "callback");

        return this.onTextChanged((TextChange textChange) -> callback.run());
    }

    /**
     * Register the provided {@link Action1} to be invoked when this {@link UITextBox}'s text is
     * changed.
     * @param callback The {@link Action1} to invoke when this {@link UITextBox}'s text is changed.
     * @return A {@link Disposable} that can be disposed to unregister the provided {@link Action1}.
     */
    public Disposable onTextChanged(Action1<TextChange> callback);

    /**
     * A version of a {@link UITextBox} that returns its own type from chainable methods.
     * @param <T> The actual type of the {@link UITextBox}.
     */
    public static interface Typed<T extends UITextBox> extends UITextBox
    {
        @Override
        public T setBackgroundColor(Color backgroundColor);

        @Override
        public T setWidth(int width);

        @Override
        public T setWidth(Distance width);

        @Override
        public T setHeight(int height);

        @Override
        public T setHeight(Distance height);

        @Override
        public T setSize(int width, int height);

        @Override
        public T setSize(Size2Integer size);

        @Override
        public T setDynamicSize(DynamicSize2Integer dynamicSize);

        @Override
        public T setText(String text);

        @Override
        public T insertText(int startIndex, String text);

        @Override
        public T removeText(int startIndex, int length);
    }
}
