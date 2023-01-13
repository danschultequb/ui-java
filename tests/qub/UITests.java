package qub;

public interface UITests
{
    public static void test(TestRunner runner, Function0<? extends UI> creator)
    {
        runner.testGroup(UI.class, () ->
        {
            runner.testGroup("setCreator(Class<U>,Function0<T>)", () ->
            {
                runner.test("with null uiElementType", (Test test) ->
                {
                    try (final UI ui = creator.run())
                    {
                        test.assertThrows(() -> ui.setCreator((Class<UIElement>)null, () -> null),
                            new PreConditionFailure("uiElementType cannot be null."));
                    }
                });

                runner.test("with null uiElementCreator", (Test test) ->
                {
                    try (final UI ui = creator.run())
                    {
                        test.assertThrows(() -> ui.setCreator(UIElement.class, null),
                            new PreConditionFailure("uiElementCreator cannot be null."));
                    }
                });
            });

            runner.testGroup("setCreator(Iterable<Class<U>>,Function0<T>)", () ->
            {
                runner.test("with null uiElementTypes", (Test test) ->
                {
                    try (final UI ui = creator.run())
                    {
                        test.assertThrows(() -> ui.setCreator((Iterable<Class<? extends UIText>>)null, () -> null),
                            new PreConditionFailure("uiElementTypes cannot be null."));
                    }
                });

                runner.test("with null uiElementCreator", (Test test) ->
                {
                    try (final UI ui = creator.run())
                    {
                        test.assertThrows(() -> ui.setCreator(Iterable.create(UIElement.class), null),
                            new PreConditionFailure("uiElementCreator cannot be null."));
                    }
                });
            });

            runner.testGroup("create(Class<? extends UIElement>)", () ->
            {
                final Action2<Class<? extends UIElement>,Throwable> createErrorTest = (Class<? extends UIElement> uiType, Throwable expected) ->
                {
                    runner.test("with " + Types.getTypeName(uiType), (Test test) ->
                    {
                        try (final UI ui = creator.run())
                        {
                            test.assertThrows(() -> ui.create(uiType).await(), expected);
                        }
                    });
                };

                createErrorTest.run(null, new PreConditionFailure("uiElementType cannot be null."));
                createErrorTest.run(UIElement.class, new NotFoundException("No UIElement creator function registered for UIElement type qub.UIElement."));

                runner.test("with UIText", (Test test) ->
                {
                    try (final UI ui = creator.run())
                    {
                        final UIText text = ui.create(UIText.class).await();
                        test.assertNotNull(text);
                        test.assertEqual("", text.getText());
                    }
                });

                runner.test("with UIButton", (Test test) ->
                {
                    try (final UI ui = creator.run())
                    {
                        final UIButton button = ui.create(UIButton.class).await();
                        test.assertNotNull(button);
                        test.assertEqual("", button.getText());
                    }
                });

                runner.test("with UIVerticalLayout", (Test test) ->
                {
                    try (final UI ui = creator.run())
                    {
                        final UIVerticalLayout layout = ui.create(UIVerticalLayout.class).await();
                        test.assertNotNull(layout);
                    }
                });

                runner.test("with UIHorizontalLayout", (Test test) ->
                {
                    try (final UI ui = creator.run())
                    {
                        final UIHorizontalLayout layout = ui.create(UIHorizontalLayout.class).await();
                        test.assertNotNull(layout);
                    }
                });

                runner.test("with UICanvas", (Test test) ->
                {
                    try (final UI ui = creator.run())
                    {
                        final UICanvas canvas = ui.create(UICanvas.class).await();
                        test.assertNotNull(canvas);
                    }
                });
            });

            runner.test("createUIText()", (Test test) ->
            {
                try (final UI ui = creator.run())
                {
                    final UIText text = ui.createUIText().await();
                    test.assertNotNull(text);
                    test.assertEqual("", text.getText());
                }
            });

            runner.test("createUIButton()", (Test test) ->
            {
                try (final UI ui = creator.run())
                {
                    final UIButton text = ui.createUIButton().await();
                    test.assertNotNull(text);
                    test.assertEqual("", text.getText());
                }
            });

            runner.test("createUIVerticalLayout()", (Test test) ->
            {
                try (final UI ui = creator.run())
                {
                    final UIVerticalLayout layout = ui.createUIVerticalLayout().await();
                    test.assertNotNull(layout);
                }
            });

            runner.test("createUIHorizontalLayout()", (Test test) ->
            {
                try (final UI ui = creator.run())
                {
                    final UIHorizontalLayout layout = ui.createUIHorizontalLayout().await();
                    test.assertNotNull(layout);
                }
            });

            runner.test("createUITextBox()", (Test test) ->
            {
                try (final UI ui = creator.run())
                {
                    final UITextBox textBox = ui.createUITextBox().await();
                    test.assertNotNull(textBox);
                }
            });

            runner.test("createUICanvas()", (Test test) ->
            {
                try (final UI ui = creator.run())
                {
                    final UICanvas canvas = ui.createUICanvas().await();
                    test.assertNotNull(canvas);
                }
            });

            runner.test("dispose()", (Test test) ->
            {
                try (final UI ui = creator.run())
                {
                    test.assertFalse(ui.isDisposed());

                    test.assertTrue(ui.dispose().await());
                    test.assertTrue(ui.isDisposed());

                    test.assertFalse(ui.dispose().await());
                    test.assertTrue(ui.isDisposed());
                }
            });
        });
    }
}
