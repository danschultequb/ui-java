package qub;

public interface UIElementTests
{
    public static void test(TestRunner runner, Function0<? extends UIElement> creator)
    {
        PreCondition.assertNotNull(runner, "runner");
        PreCondition.assertNotNull(creator, "creator");

        runner.testGroup(UIElement.class, () ->
        {
            runner.testGroup("setBackgroundColor(Color)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final UIElement uiElement = creator.run();
                    final Color initialColor = uiElement.getBackgroundColor();
                    test.assertThrows(() -> uiElement.setBackgroundColor(null),
                        new PreConditionFailure("backgroundColor cannot be null."));
                    test.assertEqual(initialColor, uiElement.getBackgroundColor());
                });

                final Action1<Color> setBackgroundColorTest = (Color color) ->
                {
                    runner.test("with " + color, (Test test) ->
                    {
                        final UIElement uiElement = creator.run();
                        final UIElement setBackgroundColorResult = uiElement.setBackgroundColor(color);
                        test.assertSame(uiElement, setBackgroundColorResult);
                        test.assertEqual(color, uiElement.getBackgroundColor());
                    });
                };

                setBackgroundColorTest.run(Color.blue);
                setBackgroundColorTest.run(Color.red);
                setBackgroundColorTest.run(Color.white);
                setBackgroundColorTest.run(Color.create(1, 2, 3, Color.ComponentMax));
            });

//            runner.testGroup("setWidth(Distance)", () ->
//            {
//                final Action2<Distance,Throwable> setWidthErrorTest = (Distance width, Throwable expected) ->
//                {
//                    runner.test("with " + width, (Test test) ->
//                    {
//                        try (final FakeDesktopProcess process = FakeDesktopProcess.create())
//                        {
//                            final UIElement uiElement = creator.run(process);
//                            test.assertThrows(() -> uiElement.setWidth(width), expected);
//                        }
//                    });
//                };
//
//                setWidthErrorTest.run(null, new PreConditionFailure("width cannot be null."));
//                setWidthErrorTest.run(Distance.inches(-1), new PreConditionFailure("width (-1.0 Inches) must be greater than or equal to 0.0 Inches."));
//
//                final Action1<Distance> setWidthTest = (Distance width) ->
//                {
//                    runner.test("with " + width, (Test test) ->
//                    {
//                        try (final FakeDesktopProcess process = FakeDesktopProcess.create())
//                        {
//                            final UIElement uiElement = creator.run(process);
//
//                            final UIElement setWidthResult = uiElement.setWidth(width);
//                            test.assertSame(uiElement, setWidthResult);
//
//                            test.assertEqual(width, uiElement.getWidth());
//                        }
//                    });
//                };
//
//                setWidthTest.run(Distance.inches(1));
//                setWidthTest.run(Distance.inches(5));
//            });
//
//            runner.testGroup("setHeight(Distance)", () ->
//            {
//                final Action2<Distance,Throwable> setHeightErrorTest = (Distance height, Throwable expected) ->
//                {
//                    runner.test("with " + height, (Test test) ->
//                    {
//                        try (final FakeDesktopProcess process = FakeDesktopProcess.create())
//                        {
//                            final UIElement uiElement = creator.run(process);
//                            test.assertThrows(() -> uiElement.setHeight(height), expected);
//                        }
//                    });
//                };
//
//                setHeightErrorTest.run(null, new PreConditionFailure("height cannot be null."));
//                setHeightErrorTest.run(Distance.inches(-1), new PreConditionFailure("height (-1.0 Inches) must be greater than or equal to 0.0 Inches."));
//
//                final Action1<Distance> setHeightTest = (Distance height) ->
//                {
//                    runner.test("with " + height, (Test test) ->
//                    {
//                        try (final FakeDesktopProcess process = FakeDesktopProcess.create())
//                        {
//                            final UIElement uiElement = creator.run(process);
//
//                            final UIElement setHeightResult = uiElement.setHeight(height);
//                            test.assertSame(uiElement, setHeightResult);
//
//                            test.assertEqual(height, uiElement.getHeight());
//                        }
//                    });
//                };
//
//                setHeightTest.run(Distance.inches(1));
//                setHeightTest.run(Distance.inches(5));
//            });
//
//            runner.testGroup("setSize(Size2D)", () ->
//            {
//                final Action2<Size2D,Throwable> setSizeErrorTest = (Size2D size, Throwable expected) ->
//                {
//                    runner.test("with " + size, (Test test) ->
//                    {
//                        try (final FakeDesktopProcess process = FakeDesktopProcess.create())
//                        {
//                            final UIElement uiElement = creator.run(process);
//                            test.assertThrows(() -> uiElement.setSize(size), expected);
//                        }
//                    });
//                };
//
//                setSizeErrorTest.run(null, new PreConditionFailure("size cannot be null."));
//
//                final Action1<Size2D> setSizeTest = (Size2D size) ->
//                {
//                    runner.test("with " + size, (Test test) ->
//                    {
//                        try (final FakeDesktopProcess process = FakeDesktopProcess.create())
//                        {
//                            final UIElement uiElement = creator.run(process);
//                            final UIElement setSizeResult = uiElement.setSize(size);
//                            test.assertSame(uiElement, setSizeResult);
//                            test.assertEqual(size, uiElement.getSize());
//                        }
//                    });
//                };
//
//                setSizeTest.run(Size2D.create(Distance.zero, Distance.zero));
//                setSizeTest.run(Size2D.create(Distance.zero, Distance.inches(1)));
//                setSizeTest.run(Size2D.create(Distance.inches(1), Distance.zero));
//                setSizeTest.run(Size2D.create(Distance.inches(2), Distance.inches(3)));
//            });
//
//            runner.testGroup("setSize(Distance,Distance)", () ->
//            {
//                final Action3<Distance,Distance,Throwable> setSizeErrorTest = (Distance width, Distance height, Throwable expected) ->
//                {
//                    runner.test("with " + English.andList(width, height), (Test test) ->
//                    {
//                        try (final FakeDesktopProcess process = FakeDesktopProcess.create())
//                        {
//                            final UIElement uiElement = creator.run(process);
//                            test.assertThrows(() -> uiElement.setSize(width, height), expected);
//                        }
//                    });
//                };
//
//                setSizeErrorTest.run(null, Distance.inches(1), new PreConditionFailure("width cannot be null."));
//                setSizeErrorTest.run(Distance.inches(-1), Distance.inches(1), new PreConditionFailure("width (-1.0 Inches) must be greater than or equal to 0.0 Inches."));
//                setSizeErrorTest.run(Distance.zero, null, new PreConditionFailure("height cannot be null."));
//                setSizeErrorTest.run(Distance.zero, Distance.inches(-1), new PreConditionFailure("height (-1.0 Inches) must be greater than or equal to 0.0 Inches."));
//
//                final Action2<Distance,Distance> setSizeTest = (Distance width, Distance height) ->
//                {
//                    runner.test("with " + English.andList(width, height), (Test test) ->
//                    {
//                        try (final FakeDesktopProcess process = FakeDesktopProcess.create())
//                        {
//                            final UIElement uiElement = creator.run(process);
//                            final UIElement setSizeResult = uiElement.setSize(width, height);
//                            test.assertSame(uiElement, setSizeResult);
//                            test.assertEqual(width, uiElement.getWidth());
//                            test.assertEqual(height, uiElement.getHeight());
//                        }
//                    });
//                };
//
//                setSizeTest.run(Distance.zero, Distance.zero);
//                setSizeTest.run(Distance.zero, Distance.inches(2));
//                setSizeTest.run(Distance.inches(1.5), Distance.zero);
//                setSizeTest.run(Distance.inches(5), Distance.inches(4));
//            });
//
//            runner.testGroup("onSizeChanged(Action0)", () ->
//            {
//                runner.test("with null", (Test test) ->
//                {
//                    try (final FakeDesktopProcess process = FakeDesktopProcess.create())
//                    {
//                        final UIElement uiElement = creator.run(process);
//                        test.assertThrows(() -> uiElement.onSizeChanged(null),
//                            new PreConditionFailure("callback cannot be null."));
//                    }
//                });
//
//                runner.test("with non-null", runner.skip(false), (Test test) ->
//                {
//                    try (final FakeDesktopProcess process = FakeDesktopProcess.create())
//                    {
//                        final UIElement uiElement = creator.run(process);
//                        final long currentThreadId = CurrentThread.getId();
//                        final LongValue eventThreadId = LongValue.create();
//
//                        final IntegerValue value = IntegerValue.create(0);
//                        final Disposable subscription = uiElement.onSizeChanged(() ->
//                        {
//                            eventThreadId.set(CurrentThread.getId());
//                            value.increment();
//                        });
//                        test.assertNotNull(subscription);
//                        test.assertFalse(subscription.isDisposed());
//                        test.assertFalse(eventThreadId.hasValue());
//                        test.assertEqual(0, value.get());
//
//                        uiElement.setSize(Distance.inches(10), Distance.inches(12));
//
//                        test.assertEqual(currentThreadId, eventThreadId.get());
//                        test.assertEqual(1, value.get());
//
//                        test.assertTrue(subscription.dispose().await());
//                        test.assertEqual(1, value.get());
//
//                        eventThreadId.clear();
//                        value.set(0);
//
//                        uiElement.setSize(Distance.inches(9), Distance.inches(11));
//
//                        test.assertFalse(eventThreadId.hasValue());
//                        test.assertEqual(0, value.get());
//                    }
//                });
//            });
//
//            runner.test("getPadding()", (Test test) ->
//            {
//                try (final FakeDesktopProcess process = FakeDesktopProcess.create())
//                {
//                    final UIElement uiElement = creator.run(process);
//                    final UIPadding padding = uiElement.getPadding();
//                    test.assertNotNull(padding);
//                    test.assertEqual(padding, uiElement.getPadding());
//                }
//            });
//
//            runner.testGroup("setPadding(UIPadding)", () ->
//            {
//                runner.test("with null", (Test test) ->
//                {
//                    try (final FakeDesktopProcess process = FakeDesktopProcess.create())
//                    {
//                        final UIElement uiElement = creator.run(process);
//                        final UIPadding padding = uiElement.getPadding();
//                        test.assertThrows(() -> uiElement.setPadding(null),
//                            new PreConditionFailure("padding cannot be null."));
//                        test.assertEqual(padding, uiElement.getPadding());
//                    }
//                });
//
//                runner.test("with non-null", (Test test) ->
//                {
//                    try (final FakeDesktopProcess process = FakeDesktopProcess.create())
//                    {
//                        final UIElement uiElement = creator.run(process);
//                        final UIPadding padding = UIPadding.create(Distance.inches(1), Distance.inches(2), Distance.inches(3), Distance.inches(4));
//                        final UIElement setPaddingResult = uiElement.setPadding(padding);
//                        test.assertSame(uiElement, setPaddingResult);
//                        test.assertEqual(padding, uiElement.getPadding());
//                    }
//                });
//            });
//
//            runner.testGroup("onPaddingChanged(Action0)", () ->
//            {
//                runner.test("with null", (Test test) ->
//                {
//                    try (final FakeDesktopProcess process = FakeDesktopProcess.create())
//                    {
//                        final UIElement uiElement = creator.run(process);
//                        test.assertThrows(() -> uiElement.onPaddingChanged((Action0)null),
//                            new PreConditionFailure("callback cannot be null."));
//                    }
//                });
//
//                runner.test("when padding set to equal padding", (Test test) ->
//                {
//                    try (final FakeDesktopProcess process = FakeDesktopProcess.create())
//                    {
//                        final UIElement uiElement = creator.run(process);
//                        final IntegerValue changes = IntegerValue.create(0);
//                        uiElement.onPaddingChanged(changes::increment);
//
//                        uiElement.setPadding(uiElement.getPadding());
//
//                        test.assertEqual(0, changes.get());
//                    }
//                });
//
//                runner.test("when padding set to different padding", (Test test) ->
//                {
//                    try (final FakeDesktopProcess process = FakeDesktopProcess.create())
//                    {
//                        final UIElement uiElement = creator.run(process);
//                        final IntegerValue changes = IntegerValue.create(0);
//                        uiElement.onPaddingChanged(changes::increment);
//
//                        uiElement.setPadding(UIPadding.create(Distance.inches(1)));
//
//                        test.assertEqual(1, changes.get());
//                    }
//                });
//            });
//
//            runner.testGroup("getContentSpaceSize()", () ->
//            {
//                runner.test("with no padding", (Test test) ->
//                {
//                    try (final FakeDesktopProcess process = FakeDesktopProcess.create())
//                    {
//                        final UIElement uiElement = creator.run(process);
//                        uiElement.setPadding(UIPadding.zero);
//
//                        final Size2D contentSpaceSize = uiElement.getContentSpaceSize();
//                        final Size2D size = uiElement.getSize();
//                        test.assertEqual(size, contentSpaceSize);
//                    }
//                });
//
//                runner.test("with padding", (Test test) ->
//                {
//                    try (final FakeDesktopProcess process = FakeDesktopProcess.create())
//                    {
//                        final UIElement uiElement = creator.run(process);
//                        final UIPadding padding = UIPadding.create(Distance.inches(0.01));
//                        uiElement.setPadding(padding);
//
//                        final Size2D contentSpaceSize = uiElement.getContentSpaceSize();
//                        final Distance width = uiElement.getWidth();
//                        final Distance expectedWidth = width.greaterThan(padding.getWidth()) ? width.minus(padding.getWidth()) : Distance.zero;
//                        final Distance height = uiElement.getHeight();
//                        final Distance expectedHeight = height.greaterThan(padding.getHeight()) ? height.minus(padding.getHeight()) : Distance.zero;
//                        final Size2D expectedSize = Size2D.create(expectedWidth, expectedHeight);
//                        test.assertEqual(expectedSize, contentSpaceSize, Size2D.create(Distance.inches(0.00001), Distance.inches(0.00001)));
//                    }
//                });
//            });
//
//            runner.testGroup("getContentSpaceWidth()", () ->
//            {
//                runner.test("with no padding", (Test test) ->
//                {
//                    try (final FakeDesktopProcess process = FakeDesktopProcess.create())
//                    {
//                        final UIElement uiElement = creator.run(process);
//                        uiElement.setPadding(UIPadding.zero);
//
//                        final Distance contentSpaceWidth = uiElement.getContentSpaceWidth();
//                        final Distance width = uiElement.getWidth();
//                        test.assertEqual(width, contentSpaceWidth);
//                    }
//                });
//
//                runner.test("with padding", (Test test) ->
//                {
//                    try (final FakeDesktopProcess process = FakeDesktopProcess.create())
//                    {
//                        final UIElement uiElement = creator.run(process);
//                        final UIPadding padding = UIPadding.create(Distance.inches(0.01));
//                        uiElement.setPadding(padding);
//
//                        final Distance contentSpaceWidth = uiElement.getContentSpaceWidth();
//                        final Distance width = uiElement.getWidth();
//                        final Distance expectedWidth = width.greaterThan(padding.getWidth()) ? width.minus(padding.getWidth()) : Distance.zero;
//                        test.assertEqual(expectedWidth, contentSpaceWidth, Distance.inches(0.00001));
//                    }
//                });
//            });
//
//            runner.testGroup("getContentSpaceHeight()", () ->
//            {
//                runner.test("with no padding", (Test test) ->
//                {
//                    try (final FakeDesktopProcess process = FakeDesktopProcess.create())
//                    {
//                        final UIElement uiElement = creator.run(process);
//                        uiElement.setPadding(UIPadding.zero);
//
//                        final Distance contentSpaceHeight = uiElement.getContentSpaceHeight();
//                        final Distance width = uiElement.getHeight();
//                        test.assertEqual(width, contentSpaceHeight);
//                    }
//                });
//
//                runner.test("with padding", (Test test) ->
//                {
//                    try (final FakeDesktopProcess process = FakeDesktopProcess.create())
//                    {
//                        final UIElement uiElement = creator.run(process);
//                        final UIPadding padding = UIPadding.create(Distance.inches(0.01));
//                        uiElement.setPadding(padding);
//
//                        final Distance contentSpaceHeight = uiElement.getContentSpaceHeight();
//                        final Distance height = uiElement.getHeight();
//                        final Distance expectedHeight = height.greaterThan(padding.getHeight()) ? height.minus(padding.getHeight()) : Distance.zero;
//                        test.assertEqual(expectedHeight, contentSpaceHeight, Distance.inches(0.00001));
//                    }
//                });
//            });
        });
    }
}
