package qub;

public class SwingUIWindow implements UIWindow.Typed<SwingUIWindow>
{
    private final SwingUI ui;

    private boolean disposed;
    private final RunnableEvent0 disposeEvent;
    private final AsyncTask<Void> disposeTask;

    private final javax.swing.JFrame jFrame;
    private UIElement content;

    private SwingUIWindow(SwingUI ui)
    {
        PreCondition.assertNotNull(ui, "ui");

        this.ui = ui;

        this.disposeEvent = Event0.create();
        this.disposeTask = this.ui.createPausedAsyncTask(Action0.empty);

        this.jFrame = new javax.swing.JFrame();
        this.jFrame.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        this.jFrame.addWindowListener(JavaWindowListenter.create()
            .setWindowOpenedAction(ui::setCurrentThreadAsyncRunner)
            .setWindowClosedAction(this::close));
    }

    public static SwingUIWindow create(SwingUI ui)
    {
        return new SwingUIWindow(ui);
    }

    @Override
    public SwingUIWindow setBackgroundColor(Color backgroundColor)
    {
        JavaAwtFrames.setBackgroundColor(this.jFrame, backgroundColor);

        return this;
    }

    @Override
    public Color getBackgroundColor()
    {
        return JavaAwtFrames.getBackgroundColor(this.jFrame);
    }

    @Override
    public SwingUIWindow setTitle(String title)
    {
        JavaAwtFrames.setTitle(this.jFrame, title);

        return this;
    }

    @Override
    public String getTitle()
    {
        return JavaAwtFrames.getTitle(this.jFrame);
    }

    @Override
    public int getContentAreaWidth()
    {
        return JavaAwtFrames.getContentAreaWidth(this.jFrame);
    }

    @Override
    public int getContentAreaHeight()
    {
        return JavaAwtFrames.getContentAreaHeight(this.jFrame);
    }

    @Override
    public Size2Integer getContentAreaSize()
    {
        return JavaAwtFrames.getContentAreaSize(this.jFrame);
    }

    @Override
    public Disposable onContentAreaSizeChanged(Action1<SizeChange> action)
    {
        return JavaAwtFrames.onContentAreaSizeChanged(this.jFrame, action);
    }

    /**
     * Get whether this {@link SwingUIWindow} is visible.
     */
    public boolean getVisible()
    {
        return JavaAwtFrames.getVisible(this.jFrame);
    }

    /**
     * Set the visibility of this {@link SwingUIWindow}.
     * @param visible Whether this {@link SwingUIWindow} is visible.
     * @return This object for method chaining.
     */
    public SwingUIWindow setVisible(boolean visible)
    {
        JavaAwtFrames.setVisible(this.jFrame, visible);

        return this;
    }

    /**
     * Run the provided {@link Action0} when this {@link SwingUIWindow} is disposed.
     * @param action The {@link Action0} to run when this {@link SwingUIWindow} is disposed.
     * @return A {@link Disposable} that can unregister the provided {@link Action0}.
     */
    public Disposable onDisposed(Action0 action)
    {
        return this.disposeEvent.subscribe(action);
    }

    @Override
    public boolean isDisposed()
    {
        return this.disposed;
    }

    @Override
    public Result<Boolean> dispose()
    {
        return Result.create(() ->
        {
            final boolean result = !this.disposed;
            if (result)
            {
                this.disposed = true;

                this.jFrame.dispose();

                this.disposeEvent.run();

                this.disposeTask.schedule();
                this.disposeTask.await();
            }
            return result;
        });
    }

    /**
     * Wait for this {@link SwingUIWindow} to be closed/disposed.
     */
    public void await()
    {
        this.disposeTask.await();
    }

    /**
     * Set the pixel width and height of this {@link SwingUIWindow}.
     * @param width The pixel width of this {@link SwingUIWindow}.
     * @param height The pixel height of this {@link SwingUIWindow}.
     * @return This object for method chaining.
     */
    public SwingUIWindow setSize(int width, int height)
    {
        PreCondition.assertNotDisposed(this, "this");

        JavaAwtFrames.setSize(this.jFrame, width, height);

        return this;
    }

    /**
     * Set the {@link Distance} width and height of this {@link SwingUIWindow}.
     * @param width The new {@link Distance} width of this {@link SwingUIWindow}.
     * @param height The new {@link Distance} height of this {@link SwingUIWindow}.
     * @return This object for method chaining.
     */
    public SwingUIWindow setSize(Distance width, Distance height)
    {
        PreCondition.assertNotDisposed(this, "this");

        JavaAwtFrames.setSize(this.jFrame, this.ui, width, height);

        return this;
    }

    /**
     * Set the pixel width of this {@link SwingUIWindow}.
     * @param width The new pixel width of this {@link SwingUIWindow}.
     * @return This object for method chaining.
     */
    public SwingUIWindow setWidth(int width)
    {
        PreCondition.assertNotDisposed(this, "this");

        JavaAwtFrames.setWidth(this.jFrame, width);

        return this;
    }

    /**
     * Set the {@link Distance} width of this {@link SwingUIWindow}.
     * @param width The new {@link Distance} width of this {@link SwingUIWindow}.
     * @return This object for method chaining.
     */
    public SwingUIWindow setWidth(Distance width)
    {
        PreCondition.assertNotDisposed(this, "this");

        JavaAwtFrames.setWidth(this.jFrame, this.ui, width);

        return this;
    }

    /**
     * Set the pixel height of this {@link SwingUIWindow}.
     * @param pixelHeight The new pixel height of this {@link SwingUIWindow}.
     * @return This object for method chaining.
     */
    public SwingUIWindow setHeight(int pixelHeight)
    {
        PreCondition.assertNotDisposed(this, "this");

        JavaAwtFrames.setHeight(this.jFrame, pixelHeight);

        return this;
    }

    /**
     * Set the height {@link Distance} of this {@link SwingUIWindow}.
     * @param height The new height of this {@link SwingUIWindow}.
     * @return This object for method chaining.
     */
    public SwingUIWindow setHeight(Distance height)
    {
        PreCondition.assertNotDisposed(this, "this");

        JavaAwtFrames.setHeight(this.jFrame, this.ui, height);

        return this;
    }

    @Override
    public int getWidth()
    {
        PreCondition.assertNotDisposed(this, "this");

        return JavaAwtFrames.getWidth(this.jFrame);
    }

    @Override
    public int getHeight()
    {
        PreCondition.assertNotDisposed(this, "this");

        return JavaAwtFrames.getHeight(this.jFrame);
    }

    @Override
    public Size2Integer getSize()
    {
        return JavaAwtFrames.getSize(this.jFrame);
    }

    @Override
    public Disposable onSizeChanged(Action1<SizeChange> action)
    {
        return JavaAwtFrames.onSizeChanged(this.jFrame, action);
    }

    @Override
    public Result<? extends UIElement> getContent()
    {
        PreCondition.assertNotDisposed(this, "this");

        return Result.create(() ->
        {
            if (this.content == null)
            {
                throw new NotFoundException("No " + Types.getTypeName(UIElement.class) + " content has been set in this " + Types.getTypeName(UIWindow.class) + ".");
            }
            final UIElement result = this.content;

            PostCondition.assertNotNull(result, "result");

            return result;
        });
    }

    @Override
    public SwingUIWindow setContent(UIElement uiElement)
    {
        PreCondition.assertNotDisposed(this, "this");
        PreCondition.assertNotNull(uiElement, "uiElement");
        PreCondition.assertInstanceOf(uiElement, JComponentUIElement.class, "uiElement");

        final JComponentUIElement jComponentUIElement = (JComponentUIElement)uiElement;
        final javax.swing.JComponent jComponent = jComponentUIElement.getJComponent();
        this.jFrame.setContentPane(jComponent);
        this.content = uiElement;

        return this;
    }
}
