package qub;

public interface EveryoneSwingUIWindowTests
{
    public static void test(TestRunner runner)
    {
        runner.testGroup(EveryoneSwingUIWindow.class,
            (TestResources resources) -> Tuple.create(resources.getMainAsyncRunner()),
            (AsyncScheduler mainAsyncRunner) ->
        {
            final Function0<EveryoneSwingUI> uiCreator = () ->
            {
                return EveryoneSwingUI.create(mainAsyncRunner)
                    .setHorizontalPixelsPerInch(200)
                    .setVerticalPixelsPerInch(100);
            };

            final Function0<EveryoneSwingUIWindow> windowCreator = () ->
            {
                final EveryoneSwingUI ui = uiCreator.run();
                return EveryoneSwingUIWindow.create(ui);
            };

            UIWindowTests.test(runner, windowCreator);

            runner.testGroup("setBackgroundColor(Color)", () ->
            {
                runner.test("check return value type", (Test test) ->
                {
                    final EveryoneSwingUIWindow uiElement = windowCreator.run();

                    final EveryoneSwingUIWindow setBackgroundColorResult = uiElement.setBackgroundColor(Color.red);
                    test.assertSame(uiElement, setBackgroundColorResult);
                    test.assertEqual(Color.red, uiElement.getBackgroundColor());
                });

                runner.testGroup("with transparent backgrounds", () ->
                {
                    final Action1<Color> setBackgroundColorErrorTest = (Color color) ->
                    {
                        runner.test("with " + color, (Test test) ->
                        {
                            final EveryoneSwingUIWindow window = windowCreator.run();
                            test.assertThrows(() -> window.setBackgroundColor(color),
                                new java.awt.IllegalComponentStateException("The frame is decorated"));
                        });
                    };

                    setBackgroundColorErrorTest.run(Color.create(1, 2, 3, 4));
                    setBackgroundColorErrorTest.run(Color.create(1, 2, 3, 0));
                    setBackgroundColorErrorTest.run(Color.transparent);
                });
            });

            runner.testGroup("setVisible(boolean)", () ->
            {
                runner.test("with false when not visible", (Test test) ->
                {
                    try (final EveryoneSwingUIWindow window = windowCreator.run())
                    {
                        test.assertFalse(window.getVisible());

                        final EveryoneSwingUIWindow setVisibleResult = window.setVisible(false);
                        test.assertSame(window, setVisibleResult);

                        test.assertFalse(window.getVisible());
                    }
                });

                runner.test("with true when not visible", (Test test) ->
                {
                    try (final EveryoneSwingUIWindow window = windowCreator.run())
                    {
                        test.assertFalse(window.getVisible());

                        final EveryoneSwingUIWindow setVisibleResult = window.setVisible(true);
                        test.assertSame(window, setVisibleResult);

                        test.assertTrue(window.getVisible());
                    }
                });

                runner.test("with false when visible", (Test test) ->
                {
                    try (final EveryoneSwingUIWindow window = windowCreator.run()
                        .setVisible(true))
                    {
                        test.assertTrue(window.getVisible());

                        final EveryoneSwingUIWindow setVisibleResult = window.setVisible(false);
                        test.assertSame(window, setVisibleResult);

                        test.assertFalse(window.getVisible());
                    }
                });

                runner.test("with true when visible", (Test test) ->
                {
                    try (final EveryoneSwingUIWindow window = windowCreator.run()
                        .setVisible(true))
                    {
                        test.assertTrue(window.getVisible());

                        final EveryoneSwingUIWindow setVisibleResult = window.setVisible(true);
                        test.assertSame(window, setVisibleResult);

                        test.assertTrue(window.getVisible());
                    }
                });
            });

            runner.testGroup("dispose()", () ->
            {
                runner.test("when not visible", (Test test) ->
                {
                    try (final EveryoneSwingUIWindow window = windowCreator.run())
                    {
                        test.assertFalse(window.isDisposed());
                        test.assertFalse(window.getVisible());

                        test.assertTrue(window.dispose().await());
                        test.assertTrue(window.isDisposed());
                        test.assertFalse(window.getVisible());
                    }
                });

                runner.test("when visible", (Test test) ->
                {
                    try (final EveryoneSwingUIWindow window = windowCreator.run()
                        .setVisible(true))
                    {
                        test.assertFalse(window.isDisposed());
                        test.assertTrue(window.getVisible());

                        test.assertTrue(window.dispose().await());
                        test.assertTrue(window.isDisposed());
                        test.assertFalse(window.getVisible());
                    }
                });
            });

            runner.testGroup("await()", () ->
            {
                runner.test("when not visible and not disposed",
                    (TestResources resources) -> Tuple.create(resources.getClock()),
                    (Test test, Clock clock) ->
                {
                    try (final EveryoneSwingUIWindow window = windowCreator.run())
                    {
                        final DateTime beforeAwait = clock.getCurrentDateTime();
                        final Duration delay = Duration.milliseconds(50);
                        clock.scheduleAfter(delay, window::close);

                        test.assertFalse(window.getVisible());
                        test.assertFalse(window.isDisposed());

                        window.await();

                        final DateTime afterAwait = clock.getCurrentDateTime();
                        test.assertGreaterThanOrEqualTo(afterAwait.minus(beforeAwait), delay, Duration.milliseconds(0.1));

                        test.assertFalse(window.getVisible());
                        test.assertTrue(window.isDisposed());
                    }
                });

                runner.test("when visible and not disposed",
                    (TestResources resources) -> Tuple.create(resources.getClock()),
                    (Test test, Clock clock) ->
                {
                    try (final EveryoneSwingUIWindow window = windowCreator.run())
                    {
                        window.setVisible(true);

                        final DateTime beforeAwait = clock.getCurrentDateTime();
                        final Duration delay = Duration.milliseconds(50);
                        clock.scheduleAfter(delay, window::close);

                        test.assertTrue(window.getVisible());
                        test.assertFalse(window.isDisposed());

                        window.await();

                        final DateTime afterAwait = clock.getCurrentDateTime();
                        test.assertGreaterThanOrEqualTo(afterAwait.minus(beforeAwait), delay, Duration.milliseconds(0.1));

                        test.assertFalse(window.getVisible());
                        test.assertTrue(window.isDisposed());
                    }
                });

                runner.test("when not visible and disposed", (Test test) ->
                {
                    try (final EveryoneSwingUIWindow window = windowCreator.run())
                    {
                        window.dispose().await();

                        test.assertFalse(window.getVisible());
                        test.assertTrue(window.isDisposed());

                        window.await();

                        test.assertFalse(window.getVisible());
                        test.assertTrue(window.isDisposed());
                    }
                });
            });

            runner.testGroup("setSize(int,int)", () ->
            {
                final Action3<Integer,Integer,Throwable> setSizeErrorTest = (Integer pixelWidth, Integer pixelHeight, Throwable expected) ->
                {
                    runner.test("with " + English.andList(pixelWidth, pixelHeight), (Test test) ->
                    {
                        try (final EveryoneSwingUIWindow window = windowCreator.run())
                        {
                            final Size2Integer pixelSize = window.getSize();

                            test.assertThrows(() -> window.setSize(pixelWidth, pixelHeight),
                                expected);

                            test.assertEqual(pixelSize, window.getSize());
                        }
                    });
                };

                setSizeErrorTest.run(-1, -1, new PreConditionFailure("width (-1) must be greater than or equal to 0."));
                setSizeErrorTest.run(-1, 0, new PreConditionFailure("width (-1) must be greater than or equal to 0."));
                setSizeErrorTest.run(0, -1, new PreConditionFailure("height (-1) must be greater than or equal to 0."));

                runner.test("when disposed", (Test test) ->
                {
                    try (final EveryoneSwingUIWindow window = windowCreator.run())
                    {
                        window.dispose().await();

                        final Size2Integer pixelSize = window.getSize();

                        test.assertThrows(() -> window.setSize(100, 100),
                            new PreConditionFailure("this.isDisposed() cannot be true."));

                        test.assertEqual(pixelSize, window.getSize());
                    }
                });

                final Action2<Integer,Integer> setSizeTest = (Integer pixelWidth, Integer pixelHeight) ->
                {
                    runner.test("with " + English.andList(pixelWidth, pixelHeight), (Test test) ->
                    {
                        try (final EveryoneSwingUIWindow window = windowCreator.run())
                        {
                            final EveryoneSwingUIWindow setSizeResult = window.setSize(pixelWidth, pixelHeight);
                            test.assertSame(window, setSizeResult);

                            test.assertEqual(MutableSize2Integer.create(pixelWidth, pixelHeight), window.getSize());
                        }
                    });
                };

                setSizeTest.run(200, 300);
                setSizeTest.run(400, 500);
            });

            runner.testGroup("setSize(Distance,Distance)", () ->
            {
                final Action3<Distance,Distance,Throwable> setSizeErrorTest = (Distance width, Distance height, Throwable expected) ->
                {
                    runner.test("with " + English.andList(width, height), (Test test) ->
                    {
                        try (final EveryoneSwingUIWindow window = windowCreator.run())
                        {
                            final Size2Integer size = window.getSize();

                            test.assertThrows(() -> window.setSize(width, height),
                                expected);

                            test.assertEqual(size, window.getSize());
                        }
                    });
                };

                setSizeErrorTest.run(Distance.inches(-1), Distance.inches(-1), new PreConditionFailure("width (-1.0 Inches) must be greater than or equal to 0.0 Inches."));
                setSizeErrorTest.run(Distance.inches(-1), Distance.inches(0), new PreConditionFailure("width (-1.0 Inches) must be greater than or equal to 0.0 Inches."));
                setSizeErrorTest.run(Distance.inches(0), Distance.inches(-1), new PreConditionFailure("height (-1.0 Inches) must be greater than or equal to 0.0 Inches."));

                runner.test("when disposed", (Test test) ->
                {
                    try (final EveryoneSwingUIWindow window = windowCreator.run())
                    {
                        window.dispose().await();

                        final Size2Integer pixelSize = window.getSize();

                        test.assertThrows(() -> window.setSize(Distance.millimeters(100), Distance.millimeters(100)),
                            new PreConditionFailure("this.isDisposed() cannot be true."));

                        test.assertEqual(pixelSize, window.getSize());
                    }
                });

                final Action2<Distance,Distance> setSizeTest = (Distance width, Distance height) ->
                {
                    runner.test("with " + English.andList(width, height), (Test test) ->
                    {
                        try (final EveryoneSwingUIWindow window = windowCreator.run())
                        {
                            final EveryoneSwingUIWindow setSizeResult = window.setSize(width, height);
                            test.assertSame(window, setSizeResult);
                        }
                    });
                };

                setSizeTest.run(Distance.inches(2), Distance.inches(3));
                setSizeTest.run(Distance.inches(3), Distance.inches(4));
            });

            runner.testGroup("setWidth(int)", () ->
            {
                final Action2<Integer,Throwable> setWidthErrorTest = (Integer pixelWidth, Throwable expected) ->
                {
                    runner.test("with " + pixelWidth, (Test test) ->
                    {
                        try (final EveryoneSwingUIWindow window = windowCreator.run())
                        {
                            final Size2Integer pixelSize = window.getSize();

                            test.assertThrows(() -> window.setWidth(pixelWidth),
                                expected);

                            test.assertEqual(pixelSize, window.getSize());
                        }
                    });
                };

                setWidthErrorTest.run(-1, new PreConditionFailure("width (-1) must be greater than or equal to 0."));

                runner.test("when disposed", (Test test) ->
                {
                    try (final EveryoneSwingUIWindow window = windowCreator.run())
                    {
                        window.dispose().await();

                        final Size2Integer pixelSize = window.getSize();

                        test.assertThrows(() -> window.setWidth(100),
                            new PreConditionFailure("this.isDisposed() cannot be true."));

                        test.assertEqual(pixelSize, window.getSize());
                    }
                });

                final Action1<Integer> setWidthTest = (Integer pixelWidth) ->
                {
                    runner.test("with " + pixelWidth, (Test test) ->
                    {
                        try (final EveryoneSwingUIWindow window = windowCreator.run())
                        {
                            final int pixelHeight = window.getHeight();

                            final EveryoneSwingUIWindow setWidthResult = window.setWidth(pixelWidth);
                            test.assertSame(window, setWidthResult);

                            test.assertEqual(pixelWidth, window.getWidth());
                            test.assertEqual(pixelHeight, window.getHeight());
                        }
                    });
                };

                setWidthTest.run(200);
                setWidthTest.run(400);
            });

            runner.testGroup("setWidth(Distance)", () ->
            {
                final Action2<Distance,Throwable> setWidthErrorTest = (Distance width, Throwable expected) ->
                {
                    runner.test("with " + width, (Test test) ->
                    {
                        try (final EveryoneSwingUIWindow window = windowCreator.run())
                        {
                            final Size2Integer pixelSize = window.getSize();

                            test.assertThrows(() -> window.setWidth(width),
                                expected);

                            test.assertEqual(pixelSize, window.getSize());
                        }
                    });
                };

                setWidthErrorTest.run(null, new PreConditionFailure("width cannot be null."));
                setWidthErrorTest.run(Distance.inches(-1), new PreConditionFailure("width (-1.0 Inches) must be greater than or equal to 0.0 Inches."));

                runner.test("when disposed", (Test test) ->
                {
                    try (final EveryoneSwingUIWindow window = windowCreator.run())
                    {
                        window.dispose().await();

                        final Size2Integer pixelSize = window.getSize();

                        test.assertThrows(() -> window.setWidth(Distance.inches(2)),
                            new PreConditionFailure("this.isDisposed() cannot be true."));

                        test.assertEqual(pixelSize, window.getSize());
                    }
                });

                final Action1<Distance> setWidthTest = (Distance width) ->
                {
                    runner.test("with " + width, (Test test) ->
                    {
                        try (final EveryoneSwingUI ui = uiCreator.run();
                             final EveryoneSwingUIWindow window = ui.createEveryoneUIWindow().await())
                        {
                            final int pixelHeight = window.getHeight();

                            final EveryoneSwingUIWindow setWidthResult = window.setWidth(width);
                            test.assertSame(window, setWidthResult);

                            test.assertEqual(ui.convertHorizontalDistanceToPixels(width), window.getWidth());
                            test.assertEqual(pixelHeight, window.getHeight());
                        }
                    });
                };

                setWidthTest.run(Distance.inches(2));
                setWidthTest.run(Distance.centimeters(4));
            });

            runner.testGroup("setHeight(int)", () ->
            {
                final Action2<Integer,Throwable> setHeightErrorTest = (Integer pixelHeight, Throwable expected) ->
                {
                    runner.test("with " + pixelHeight, (Test test) ->
                    {
                        try (final EveryoneSwingUIWindow window = windowCreator.run())
                        {
                            final Size2Integer pixelSize = window.getSize();

                            test.assertThrows(() -> window.setHeight(pixelHeight),
                                expected);

                            test.assertEqual(pixelSize, window.getSize());
                        }
                    });
                };

                setHeightErrorTest.run(-1, new PreConditionFailure("height (-1) must be greater than or equal to 0."));

                runner.test("when disposed", (Test test) ->
                {
                    try (final EveryoneSwingUIWindow window = windowCreator.run())
                    {
                        window.dispose().await();

                        final Size2Integer pixelSize = window.getSize();

                        test.assertThrows(() -> window.setHeight(100),
                            new PreConditionFailure("this.isDisposed() cannot be true."));

                        test.assertEqual(pixelSize, window.getSize());
                    }
                });

                final Action1<Integer> setHeightTest = (Integer pixelHeight) ->
                {
                    runner.test("with " + pixelHeight, (Test test) ->
                    {
                        try (final EveryoneSwingUIWindow window = windowCreator.run())
                        {
                            final int pixelWidth = window.getWidth();

                            final EveryoneSwingUIWindow setHeightResult = window.setHeight(pixelHeight);
                            test.assertSame(window, setHeightResult);

                            test.assertEqual(pixelWidth, window.getWidth());
                            test.assertEqual(pixelHeight, window.getHeight());
                        }
                    });
                };

                setHeightTest.run(200);
                setHeightTest.run(400);
            });

            runner.testGroup("setHeight(Distance)", () ->
            {
                final Action2<Distance,Throwable> setHeightErrorTest = (Distance height, Throwable expected) ->
                {
                    runner.test("with " + height, (Test test) ->
                    {
                        try (final EveryoneSwingUIWindow window = windowCreator.run())
                        {
                            final Size2Integer pixelSize = window.getSize();

                            test.assertThrows(() -> window.setHeight(height),
                                expected);

                            test.assertEqual(pixelSize, window.getSize());
                        }
                    });
                };

                setHeightErrorTest.run(null, new PreConditionFailure("height cannot be null."));
                setHeightErrorTest.run(Distance.inches(-1), new PreConditionFailure("height (-1.0 Inches) must be greater than or equal to 0.0 Inches."));

                runner.test("when disposed", (Test test) ->
                {
                    try (final EveryoneSwingUIWindow window = windowCreator.run())
                    {
                        window.dispose().await();

                        final Size2Integer pixelSize = window.getSize();

                        test.assertThrows(() -> window.setHeight(Distance.inches(2)),
                            new PreConditionFailure("this.isDisposed() cannot be true."));

                        test.assertEqual(pixelSize, window.getSize());
                    }
                });

                final Action1<Distance> setHeightTest = (Distance height) ->
                {
                    runner.test("with " + height, (Test test) ->
                    {
                        try (final EveryoneSwingUI ui = uiCreator.run();
                             final EveryoneSwingUIWindow window = ui.createEveryoneUIWindow().await())
                        {
                            final int pixelWidth = window.getWidth();

                            final EveryoneSwingUIWindow setHeightResult = window.setHeight(height);
                            test.assertSame(window, setHeightResult);

                            test.assertEqual(pixelWidth, window.getWidth());
                            test.assertEqual(ui.convertVerticalDistanceToPixels(height), window.getHeight());
                        }
                    });
                };

                setHeightTest.run(Distance.inches(2));
                setHeightTest.run(Distance.centimeters(4));
            });

            runner.testGroup("getContentAreaWidth()", () ->
            {
                runner.test("when not visible", (Test test) ->
                {
                    try (final EveryoneSwingUIWindow window = windowCreator.run())
                    {
                        final int contentAreaWidth = window.getContentAreaWidth();
                        test.assertGreaterThanOrEqualTo(contentAreaWidth, 0);
                        test.assertGreaterThanOrEqualTo(window.getWidth(), contentAreaWidth);
                    }
                });

                runner.test("when visible", (Test test) ->
                {
                    try (final EveryoneSwingUIWindow window = windowCreator.run())
                    {
                        window.setVisible(true);

                        final int contentAreaWidth = window.getContentAreaWidth();
                        test.assertGreaterThanOrEqualTo(contentAreaWidth, 0);
                        test.assertGreaterThanOrEqualTo(window.getWidth(), contentAreaWidth);
                    }
                });
            });

            runner.testGroup("getContentAreaHeight()", () ->
            {
                runner.test("when not visible", (Test test) ->
                {
                    try (final EveryoneSwingUIWindow window = windowCreator.run())
                    {
                        final int contentAreaHeight = window.getContentAreaHeight();
                        test.assertGreaterThanOrEqualTo(contentAreaHeight, 0);
                        test.assertGreaterThanOrEqualTo(window.getHeight(), contentAreaHeight);
                    }
                });

                runner.test("when visible", (Test test) ->
                {
                    try (final EveryoneSwingUIWindow window = windowCreator.run())
                    {
                        window.setVisible(true);

                        final int contentAreaHeight = window.getContentAreaHeight();
                        test.assertGreaterThan(contentAreaHeight, 0);
                        test.assertGreaterThanOrEqualTo(window.getHeight(), contentAreaHeight);
                    }
                });
            });

            runner.testGroup("getContentAreaSize()", () ->
            {
                runner.test("when not visible", (Test test) ->
                {
                    try (final EveryoneSwingUIWindow window = windowCreator.run())
                    {
                        final Size2Integer contentAreaSize = window.getContentAreaSize();
                        test.assertGreaterThanOrEqualTo(contentAreaSize.getWidth(), 0);
                        test.assertGreaterThanOrEqualTo(window.getWidth(), contentAreaSize.getWidth());
                        test.assertGreaterThanOrEqualTo(contentAreaSize.getHeight(), 0);
                        test.assertGreaterThanOrEqualTo(window.getHeight(), contentAreaSize.getHeight());
                    }
                });

                runner.test("when visible", (Test test) ->
                {
                    try (final EveryoneSwingUIWindow window = windowCreator.run())
                    {
                        window.setVisible(true);

                        final Size2Integer contentAreaSize = window.getContentAreaSize();
                        test.assertGreaterThanOrEqualTo(contentAreaSize.getWidth(), 0);
                        test.assertGreaterThanOrEqualTo(window.getWidth(), contentAreaSize.getWidth());
                        test.assertGreaterThanOrEqualTo(contentAreaSize.getHeight(), 0);
                        test.assertGreaterThanOrEqualTo(window.getHeight(), contentAreaSize.getHeight());
                    }
                });
            });

            runner.testGroup("getContent()", () ->
            {
                runner.test("before content has been set", (Test test) ->
                {
                    try (final EveryoneSwingUIWindow window = windowCreator.run())
                    {
                        test.assertThrows(() -> window.getContent().await(),
                            new NotFoundException("No UIElement content has been set in this UIWindow."));
                    }
                });
            });

            runner.testGroup("setContent(UIElement)", () ->
            {
                runner.test("with JComponentUIElement", (Test test) ->
                {
                    try (final EveryoneSwingUI ui = uiCreator.run();
                         final EveryoneSwingUIWindow window = EveryoneSwingUIWindow.create(ui))
                    {
                        final EveryoneUIText uiText = ui.create(EveryoneUIText.class).await();
                        final EveryoneSwingUIWindow setContentResult = window.setContent(uiText);
                        test.assertSame(window, setContentResult);
                        test.assertSame(uiText, window.getContent().await());
                    }
                });
            });

            runner.test("sandbox", runner.skip(false), (Test test) ->
            {
                try (final EveryoneSwingUI ui = uiCreator.run();
                     final EveryoneSwingUIWindow window = ui.createEveryoneUIWindow().await())
                {
                    window.setWidth(Distance.inches(3));
                    window.setHeight(Distance.inches(3));

                    final EveryoneUICanvas canvas = ui.create(EveryoneUICanvas.class).await();
                    canvas.setBackgroundColor(Color.green);
                    canvas.setDynamicSize(window.getContentAreaDynamicSize().scaleWidth(0.3));
                    canvas.setPaintAction((UIPainter painter) ->
                    {
                        final int canvasWidth = canvas.getWidth();
                        final int canvasHeight = canvas.getHeight();

                        final int leftX;
                        final int topY;
                        final int boardSize;
                        if (canvasWidth < canvasHeight)
                        {
                            boardSize = canvasWidth;
                            leftX = 0;
                            topY = (canvasHeight - canvasWidth) / 2;
                        }
                        else
                        {
                            boardSize = canvasHeight;
                            leftX = (canvasWidth - canvasHeight) / 2;
                            topY = 0;
                        }
                        final int rightX = leftX + boardSize;
                        final int bottomY = topY + boardSize;

                        final int oneThirdSize = boardSize / 3;
                        final int twoThirdSize = oneThirdSize * 2;

                        final int leftLineX = leftX + oneThirdSize;
                        final int rightLineX = leftX + twoThirdSize;

                        final int topLineY = topY + oneThirdSize;
                        final int bottomLineY = topY + twoThirdSize;

                        painter.drawLine(leftLineX, topY, leftLineX, bottomY);
                        painter.drawLine(rightLineX, topY, rightLineX, bottomY);
                        painter.drawLine(leftX, topLineY, rightX, topLineY);
                        painter.drawLine(leftX, bottomLineY, rightX, bottomLineY);

                        painter.drawOval(leftX, topY, oneThirdSize, oneThirdSize);
                        painter.drawOval(leftLineX, topLineY, oneThirdSize, oneThirdSize);
                        painter.drawOval(rightLineX, bottomLineY, oneThirdSize, oneThirdSize);
                    });

                    window.setContent(canvas);

                    window.setVisible(true);
                    window.await();
                }
            });
        });
    }
}
