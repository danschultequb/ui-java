package qub;

public interface UIButtonTests
{
    static void test(TestRunner runner, Function0<? extends UIButton> creator)
    {
        PreCondition.assertNotNull(runner, "runner");
        PreCondition.assertNotNull(creator, "creator");

        runner.testGroup(UIButton.class, () ->
        {
            UIElementTests.test(runner, creator);

            runner.testGroup("setBackgroundColor(Color)", () ->
            {
                runner.test("check return value type", (Test test) ->
                {
                    final UIButton uiElement = creator.run();

                    final UIButton setBackgroundColorResult = uiElement.setBackgroundColor(Color.red);
                    test.assertSame(uiElement, setBackgroundColorResult);
                    test.assertEqual(Color.red, uiElement.getBackgroundColor());
                });
            });

//            runner.testGroup("setWidth(Distance)", () ->
//            {
//                runner.test("should return " + Types.getTypeName(UIButton.class), (Test test) ->
//                {
//                    try (final FakeDesktopProcess process = FakeDesktopProcess.create())
//                    {
//                        final UIButton button = creator.run(process);
//                        final UIButton setWidthResult = button.setWidth(Distance.inches(1));
//                        test.assertSame(button, setWidthResult);
//                    }
//                });
//            });
//
//            runner.testGroup("setHeight(Distance)", () ->
//            {
//                runner.test("should return " + Types.getTypeName(UIButton.class), (Test test) ->
//                {
//                    try (final FakeDesktopProcess process = FakeDesktopProcess.create())
//                    {
//                        final UIButton button = creator.run(process);
//                        final UIButton setHeightResult = button.setHeight(Distance.inches(1));
//                        test.assertSame(button, setHeightResult);
//                    }
//                });
//            });
//
//            runner.testGroup("setSize(Size2D)", () ->
//            {
//                runner.test("should return " + Types.getTypeName(UIButton.class), (Test test) ->
//                {
//                    try (final FakeDesktopProcess process = FakeDesktopProcess.create())
//                    {
//                        final UIButton button = creator.run(process);
//                        final UIButton setHeightResult = button.setSize(Size2D.create(Distance.inches(2), Distance.inches(3)));
//                        test.assertSame(button, setHeightResult);
//                    }
//                });
//            });
//
//            runner.testGroup("setSize(Distance,Distance)", () ->
//            {
//                runner.test("should return " + Types.getTypeName(UIButton.class), (Test test) ->
//                {
//                    try (final FakeDesktopProcess process = FakeDesktopProcess.create())
//                    {
//                        final UIButton button = creator.run(process);
//                        final UIButton setHeightResult = button.setSize(Distance.inches(2), Distance.inches(3));
//                        test.assertSame(button, setHeightResult);
//                    }
//                });
//            });

            runner.testGroup("setText(String)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final UIButton button = creator.run();

                    test.assertThrows(() -> button.setText(null),
                        new PreConditionFailure("text cannot be null."));

                    test.assertEqual("", button.getText());
                });

                runner.test("with empty", (Test test) ->
                {
                    final UIButton button = creator.run();

                    final UIButton setTextResult = button.setText("");
                    test.assertSame(button, setTextResult);

                    test.assertEqual("", button.getText());
                });

                runner.test("with non-empty", (Test test) ->
                {
                    final UIButton button = creator.run();

                    final UIButton setTextResult = button.setText("hello");
                    test.assertSame(button, setTextResult);

                    test.assertEqual("hello", button.getText());
                });
            });

//            runner.testGroup("setFontSize(Distance)", () ->
//            {
//                final Action2<Distance,Throwable> setFontSizeErrorTest = (Distance fontSize, Throwable expected) ->
//                {
//                    runner.test("with " + fontSize, (Test test) ->
//                    {
//                        try (final FakeDesktopProcess process = FakeDesktopProcess.create())
//                        {
//                            final UIButton button = creator.run(process);
//                            test.assertThrows(() -> button.setFontSize(fontSize), expected);
//                        }
//                    });
//                };
//
//                setFontSizeErrorTest.run(null, new PreConditionFailure("fontSize cannot be null."));
//                setFontSizeErrorTest.run(Distance.inches(-1), new PreConditionFailure("fontSize (-1.0 Inches) must be greater than or equal to 0.0 Inches."));
//
//                final Action1<Distance> setFontSizeTest = (Distance fontSize) ->
//                {
//                    runner.test("with " + fontSize, (Test test) ->
//                    {
//                        try (final FakeDesktopProcess process = FakeDesktopProcess.create())
//                        {
//                            final UIButton button = creator.run(process);
//                            final UIButton setFontSizeResult = button.setFontSize(fontSize);
//                            test.assertSame(button, setFontSizeResult);
//                            test.assertEqual(fontSize, button.getFontSize());
//                        }
//                    });
//                };
//
//                setFontSizeTest.run(Distance.zero);
//                setFontSizeTest.run(Distance.fontPoints(12));
//                setFontSizeTest.run(Distance.inches(1));
//            });

            runner.testGroup("onClick(Action0)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final UIButton button = creator.run();
                    test.assertThrows(() -> button.onClick(null),
                        new PreConditionFailure("callback cannot be null."));
                });

                runner.test("with callback that throws an exception", (Test test) ->
                {
                    final UIButton button = creator.run();

                    final Disposable subscription = button.onClick(() ->
                    {
                        throw new RuntimeException("hello");
                    });
                    test.assertNotNull(subscription);

                    test.assertTrue(subscription.dispose().await());
                });

                runner.test("with callback that doesn't throw an exception", (Test test) ->
                {
                    final UIButton button = creator.run();

                    final Disposable subscription = button.onClick(Action0.empty);
                    test.assertNotNull(subscription);

                    test.assertTrue(subscription.dispose().await());
                });
            });
        });
    }
}
