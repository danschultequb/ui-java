package qub;

public class EveryoneSwingUIWindow implements UIWindow.Typed<EveryoneSwingUIWindow>, EveryoneUIElementParent
{
    private final EveryoneSwingUI ui;

    private boolean disposed;
    private final RunnableEvent0 disposeEvent;
    private final AsyncTask<Void> disposeTask;

    private final javax.swing.JFrame jFrame;
    private EveryoneUIElement content;

    private EveryoneSwingUIWindow(EveryoneSwingUI ui)
    {
        PreCondition.assertNotNull(ui, "ui");

        this.ui = ui;
        this.disposeEvent = Event0.create();
        this.disposeTask = this.ui.createPausedAsyncTask(Action0.empty);

        this.jFrame = new javax.swing.JFrame();
        this.jFrame.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        this.jFrame.addWindowListener(JavaWindowListenter.create()
            .setWindowOpenedAction(this.ui::setCurrentThreadAsyncRunner)
            .setWindowClosedAction(this::close));
        this.jFrame.setContentPane(JComponentCanvas.create()
            .setPaintAction(this::paint));
    }

    public static EveryoneSwingUIWindow create(EveryoneSwingUI ui)
    {
        return new EveryoneSwingUIWindow(ui);
    }

    @Override
    public Color getBackgroundColor()
    {
        return JavaAwtFrames.getBackgroundColor(this.jFrame);
    }

    @Override
    public EveryoneSwingUIWindow setBackgroundColor(Color backgroundColor)
    {
        JavaAwtFrames.setBackgroundColor(this.jFrame, backgroundColor);

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

    @Override
    public EveryoneSwingUIWindow setTitle(String title)
    {
        JavaAwtFrames.setTitle(this.jFrame, title);

        return this;
    }

    @Override
    public int getWidth()
    {
        return JavaAwtFrames.getWidth(this.jFrame);
    }

    /**
     * Set the pixel width of this {@link EveryoneSwingUIWindow}.
     * @param width The pixel width of this {@link EveryoneSwingUIWindow}.
     * @return This object for method chaining.
     */
    public EveryoneSwingUIWindow setWidth(int width)
    {
        PreCondition.assertNotDisposed(this, "this");

        JavaAwtFrames.setWidth(this.jFrame, width);

        return this;
    }

    /**
     * Set the {@link Distance} width of this {@link EveryoneSwingUIWindow}.
     * @param width The {@link Distance} width of this {@link EveryoneSwingUIWindow}.
     * @return This object for method chaining.
     */
    public EveryoneSwingUIWindow setWidth(Distance width)
    {
        PreCondition.assertNotDisposed(this, "this");

        JavaAwtFrames.setWidth(this.jFrame, this.ui, width);

        return this;
    }

    @Override
    public int getHeight()
    {
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

    /**
     * Set the pixel height of this {@link EveryoneSwingUIWindow}.
     * @param height The pixel height of this {@link EveryoneSwingUIWindow}.
     * @return This object for method chaining.
     */
    public EveryoneSwingUIWindow setHeight(int height)
    {
        PreCondition.assertNotDisposed(this, "this");

        JavaAwtFrames.setHeight(this.jFrame, height);

        return this;
    }

    /**
     * Set the {@link Distance} height of this {@link EveryoneSwingUIWindow}.
     * @param height The {@link Distance} height of this {@link EveryoneSwingUIWindow}.
     * @return This object for method chaining.
     */
    public EveryoneSwingUIWindow setHeight(Distance height)
    {
        PreCondition.assertNotDisposed(this, "this");

        JavaAwtFrames.setHeight(this.jFrame, this.ui, height);

        return this;
    }

    /**
     * Set the pixel width and height of this {@link EveryoneSwingUIWindow}.
     * @param width The pixel width of this {@link EveryoneSwingUIWindow}.
     * @param height The pixel height of this {@link EveryoneSwingUIWindow}.
     * @return This object for method chaining.
     */
    public EveryoneSwingUIWindow setSize(int width, int height)
    {
        PreCondition.assertNotDisposed(this, "this");

        JavaAwtFrames.setSize(this.jFrame, width, height);

        return this;
    }

    /**
     * Set the {@link Distance} width and height of this {@link EveryoneSwingUIWindow}.
     * @param width The new {@link Distance} width of this {@link EveryoneSwingUIWindow}.
     * @param height The new {@link Distance} height of this {@link EveryoneSwingUIWindow}.
     * @return This object for method chaining.
     */
    public EveryoneSwingUIWindow setSize(Distance width, Distance height)
    {
        PreCondition.assertNotDisposed(this, "this");

        JavaAwtFrames.setSize(this.jFrame, this.ui, width, height);

        return this;
    }

    /**
     * Set the pixel size of this {@link EveryoneSwingUIWindow}.
     * @param size The new pixel size of this {@link EveryoneSwingUIWindow}.
     * @return This object for method chaining.
     */
    public EveryoneSwingUIWindow setSize(Size2Integer size)
    {
        PreCondition.assertNotNull(size, "size");

        return this.setSize(size.getWidthAsInt(), size.getHeightAsInt());
    }

    /**
     * Set the {@link Distance} size of this {@link EveryoneSwingUIWindow}.
     * @param size The new {@link Distance} size of this {@link EveryoneSwingUIWindow}.
     * @return This object for method chaining.
     */
    public EveryoneSwingUIWindow setSize(Size2Distance size)
    {
        PreCondition.assertNotNull(size, "size");

        return this.setSize(size.getWidth(), size.getHeight());
    }

    /**
     * Get whether this {@link EveryoneSwingUIWindow} is visible.
     */
    public boolean getVisible()
    {
        return JavaAwtFrames.getVisible(this.jFrame);
    }

    /**
     * Set the visibility of this {@link EveryoneSwingUIWindow}.
     * @param visible Whether this {@link EveryoneSwingUIWindow} is visible.
     * @return This object for method chaining.
     */
    public EveryoneSwingUIWindow setVisible(boolean visible)
    {
        PreCondition.assertNotDisposed(this, "this");

        JavaAwtFrames.setVisible(this.jFrame, visible);

        return this;
    }

    @Override
    public Result<? extends EveryoneUIElement> getContent()
    {
        return Result.create(() ->
        {
            if (this.content == null)
            {
                throw new NotFoundException("No " + Types.getTypeName(UIElement.class) + " content has been set in this " + Types.getTypeName(UIWindow.class) + ".");
            }
            final EveryoneUIElement result = this.content;

            PostCondition.assertNotNull(result, "result");

            return result;
        });
    }

    @Override
    public EveryoneSwingUIWindow setContent(UIElement uiElement)
    {
        PreCondition.assertNotNull(uiElement, "uiElement");
        PreCondition.assertInstanceOf(uiElement, EveryoneUIElement.class, "uiElement");

        if (this.content != null)
        {
            this.content.setParent(null);
        }

        this.content = (EveryoneUIElement)uiElement;
        this.content.setParent(this);
        this.repaint();

        return this;
    }

    /**
     * Paint this {@link EveryoneSwingUIWindow} using the provided {@link UIPainter}.
     * @param painter The {@link UIPainter} to use to paint this {@link EveryoneSwingUIWindow}.
     */
    public void paint(UIPainter painter)
    {
        PreCondition.assertNotNull(painter, "painter");

        if (this.content != null)
        {
            try (final Disposable savedState = painter.saveState())
            {
                painter.setClip(this.content.getWidth(), this.content.getHeight());
                this.content.paint(painter);
            }
        }
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
     * Register the provided {@link Action0} to be invoked when this {@link EveryoneSwingUIWindow} is
     * disposed.
     * @param callback The {@link Action0} to invoked when this {@link EveryoneSwingUIWindow} is
     *                 disposed.
     */
    public Disposable onDisposed(Action0 callback)
    {
        PreCondition.assertNotNull(callback, "callback");

        return this.disposeEvent.subscribe(callback);
    }

    /**
     * Wait for this {@link EveryoneSwingUIWindow} to be closed/disposed.
     */
    public void await()
    {
        this.disposeTask.await();
    }

    @Override
    public void repaint()
    {
        this.jFrame.repaint();
    }
}
