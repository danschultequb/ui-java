package qub;

public interface SwingUIWindowTests
{
    public static void test(TestRunner runner)
    {
        runner.testGroup(SwingUIWindow.class,
            (TestResources resources) -> Tuple.create(resources.getMainAsyncRunner()),
            (AsyncScheduler mainAsyncRunner) ->
        {
            final Function0<SwingUIWindow> creator = () ->
            {
                final SwingUI ui = SwingUI.create(mainAsyncRunner);
                return SwingUIWindow.create(ui);
            };

            UIWindowTests.test(runner, creator);

            runner.testGroup("setBackgroundColor(Color)", () ->
            {
                runner.test("check return value type", (Test test) ->
                {
                    final SwingUIWindow uiElement = creator.run();

                    final SwingUIWindow setBackgroundColorResult = uiElement.setBackgroundColor(Color.red);
                    test.assertSame(uiElement, setBackgroundColorResult);
                    test.assertEqual(Color.red, uiElement.getBackgroundColor());
                });

                runner.testGroup("with transparent backgrounds", () ->
                {
                    final Action1<Color> setBackgroundColorErrorTest = (Color color) ->
                    {
                        runner.test("with " + color, (Test test) ->
                        {
                            final UIElement uiElement = creator.run();
                            test.assertThrows(() -> uiElement.setBackgroundColor(color),
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
                    try (final SwingUI ui = SwingUI.create(mainAsyncRunner))
                    {
                        try (final SwingUIWindow window = SwingUIWindow.create(ui))
                        {
                            test.assertFalse(window.getVisible());

                            final SwingUIWindow setVisibleResult = window.setVisible(false);
                            test.assertSame(window, setVisibleResult);

                            test.assertFalse(window.getVisible());
                        }
                    }
                });

                runner.test("with true when not visible", (Test test) ->
                {
                    try (final SwingUI ui = SwingUI.create(mainAsyncRunner))
                    {
                        try (final SwingUIWindow window = SwingUIWindow.create(ui))
                        {
                            test.assertFalse(window.getVisible());

                            final SwingUIWindow setVisibleResult = window.setVisible(true);
                            test.assertSame(window, setVisibleResult);

                            test.assertTrue(window.getVisible());
                        }
                    }
                });

                runner.test("with false when visible", (Test test) ->
                {
                    try (final SwingUI ui = SwingUI.create(mainAsyncRunner))
                    {
                        try (final SwingUIWindow window = SwingUIWindow.create(ui)
                            .setVisible(true))
                        {
                            test.assertTrue(window.getVisible());

                            final SwingUIWindow setVisibleResult = window.setVisible(false);
                            test.assertSame(window, setVisibleResult);

                            test.assertFalse(window.getVisible());
                        }
                    }
                });

                runner.test("with true when visible", (Test test) ->
                {
                    try (final SwingUI ui = SwingUI.create(mainAsyncRunner))
                    {
                        try (final SwingUIWindow window = SwingUIWindow.create(ui)
                            .setVisible(true))
                        {
                            test.assertTrue(window.getVisible());

                            final SwingUIWindow setVisibleResult = window.setVisible(true);
                            test.assertSame(window, setVisibleResult);

                            test.assertTrue(window.getVisible());
                        }
                    }
                });
            });

            runner.testGroup("dispose()", () ->
            {
                runner.test("when not visible", (Test test) ->
                {
                    try (final SwingUI ui = SwingUI.create(mainAsyncRunner))
                    {
                        try (final SwingUIWindow window = SwingUIWindow.create(ui))
                        {
                            test.assertFalse(window.isDisposed());
                            test.assertFalse(window.getVisible());

                            test.assertTrue(window.dispose().await());
                            test.assertTrue(window.isDisposed());
                            test.assertFalse(window.getVisible());
                        }
                    }
                });

                runner.test("when visible", (Test test) ->
                {
                    try (final SwingUI ui = SwingUI.create(mainAsyncRunner))
                    {
                        try (final SwingUIWindow window = SwingUIWindow.create(ui)
                            .setVisible(true))
                        {
                            test.assertFalse(window.isDisposed());
                            test.assertTrue(window.getVisible());

                            test.assertTrue(window.dispose().await());
                            test.assertTrue(window.isDisposed());
                            test.assertFalse(window.getVisible());
                        }
                    }
                });
            });

            runner.testGroup("await()", () ->
            {
                runner.test("when not visible and not disposed",
                    (TestResources resources) -> Tuple.create(resources.getClock()),
                    (Test test, Clock clock) ->
                {
                    try (final SwingUI ui = SwingUI.create(mainAsyncRunner))
                    {
                        try (final SwingUIWindow window = SwingUIWindow.create(ui))
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
                    }
                });

                runner.test("when visible and not disposed",
                    (TestResources resources) -> Tuple.create(resources.getClock()),
                    (Test test, Clock clock) ->
                {
                    try (final SwingUI ui = SwingUI.create(mainAsyncRunner))
                    {
                        try (final SwingUIWindow window = SwingUIWindow.create(ui))
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
                    }
                });

                runner.test("when not visible and disposed", (Test test) ->
                {
                    try (final SwingUI ui = SwingUI.create(mainAsyncRunner))
                    {
                        try (final SwingUIWindow window = SwingUIWindow.create(ui))
                        {
                            window.dispose().await();

                            test.assertFalse(window.getVisible());
                            test.assertTrue(window.isDisposed());

                            window.await();

                            test.assertFalse(window.getVisible());
                            test.assertTrue(window.isDisposed());
                        }
                    }
                });
            });

            runner.testGroup("setSize(int,int)", () ->
            {
                final Action3<Integer,Integer,Throwable> setSizeErrorTest = (Integer pixelWidth, Integer pixelHeight, Throwable expected) ->
                {
                    runner.test("with " + English.andList(pixelWidth, pixelHeight), (Test test) ->
                    {
                        try (final SwingUI ui = SwingUI.create(mainAsyncRunner))
                        {
                            try (final SwingUIWindow window = SwingUIWindow.create(ui))
                            {
                                final Size2Integer pixelSize = window.getSize();

                                test.assertThrows(() -> window.setSize(pixelWidth, pixelHeight),
                                    expected);

                                test.assertEqual(pixelSize, window.getSize());
                            }
                        }
                    });
                };

                setSizeErrorTest.run(-1, -1, new PreConditionFailure("pixelWidth (-1) must be greater than or equal to 0."));
                setSizeErrorTest.run(-1, 0, new PreConditionFailure("pixelWidth (-1) must be greater than or equal to 0."));
                setSizeErrorTest.run(0, -1, new PreConditionFailure("pixelHeight (-1) must be greater than or equal to 0."));

                runner.test("when disposed", (Test test) ->
                {
                    try (final SwingUI ui = SwingUI.create(mainAsyncRunner))
                    {
                        try (final SwingUIWindow window = SwingUIWindow.create(ui))
                        {
                            window.dispose().await();

                            final Size2Integer pixelSize = window.getSize();

                            test.assertThrows(() -> window.setSize(100, 100),
                                new PreConditionFailure("this.isDisposed() cannot be true."));

                            test.assertEqual(pixelSize, window.getSize());
                        }
                    }
                });

                final Action2<Integer,Integer> setSizeTest = (Integer pixelWidth, Integer pixelHeight) ->
                {
                    runner.test("with " + English.andList(pixelWidth, pixelHeight), (Test test) ->
                    {
                        try (final SwingUI ui = SwingUI.create(mainAsyncRunner))
                        {
                            try (final SwingUIWindow window = SwingUIWindow.create(ui))
                            {
                                final SwingUIWindow setSizeResult = window.setSize(pixelWidth, pixelHeight);
                                test.assertSame(window, setSizeResult);

                                test.assertEqual(MutableSize2Integer.create(pixelWidth, pixelHeight), window.getSize());
                            }
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
                        try (final SwingUI ui = SwingUI.create(mainAsyncRunner))
                        {
                            try (final SwingUIWindow window = SwingUIWindow.create(ui))
                            {
                                final Size2Integer size = window.getSize();

                                test.assertThrows(() -> window.setSize(width, height),
                                    expected);

                                test.assertEqual(size, window.getSize());
                            }
                        }
                    });
                };

                setSizeErrorTest.run(Distance.inches(-1), Distance.inches(-1), new PreConditionFailure("width (-1.0 Inches) must be greater than or equal to 0.0 Inches."));
                setSizeErrorTest.run(Distance.inches(-1), Distance.inches(0), new PreConditionFailure("width (-1.0 Inches) must be greater than or equal to 0.0 Inches."));
                setSizeErrorTest.run(Distance.inches(0), Distance.inches(-1), new PreConditionFailure("height (-1.0 Inches) must be greater than or equal to 0.0 Inches."));

                runner.test("when disposed", (Test test) ->
                {
                    try (final SwingUI ui = SwingUI.create(mainAsyncRunner))
                    {
                        try (final SwingUIWindow window = SwingUIWindow.create(ui))
                        {
                            window.dispose().await();

                            final Size2Integer pixelSize = window.getSize();

                            test.assertThrows(() -> window.setSize(Distance.millimeters(100), Distance.millimeters(100)),
                                new PreConditionFailure("this.isDisposed() cannot be true."));

                            test.assertEqual(pixelSize, window.getSize());
                        }
                    }
                });

                final Action2<Distance,Distance> setSizeTest = (Distance width, Distance height) ->
                {
                    runner.test("with " + English.andList(width, height), (Test test) ->
                    {
                        try (final SwingUI ui = SwingUI.create(mainAsyncRunner))
                        {
                            try (final SwingUIWindow window = SwingUIWindow.create(ui))
                            {
                                final SwingUIWindow setSizeResult = window.setSize(width, height);
                                test.assertSame(window, setSizeResult);

                                test.assertEqual(Size2.create(width, height), window.getSizeDistances());
                            }
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
                        try (final SwingUI ui = SwingUI.create(mainAsyncRunner))
                        {
                            try (final SwingUIWindow window = SwingUIWindow.create(ui))
                            {
                                final Size2Integer pixelSize = window.getSize();

                                test.assertThrows(() -> window.setWidth(pixelWidth),
                                    expected);

                                test.assertEqual(pixelSize, window.getSize());
                            }
                        }
                    });
                };

                setWidthErrorTest.run(-1, new PreConditionFailure("pixelWidth (-1) must be greater than or equal to 0."));

                runner.test("when disposed", (Test test) ->
                {
                    try (final SwingUI ui = SwingUI.create(mainAsyncRunner))
                    {
                        try (final SwingUIWindow window = SwingUIWindow.create(ui))
                        {
                            window.dispose().await();

                            final Size2Integer pixelSize = window.getSize();

                            test.assertThrows(() -> window.setWidth(100),
                                new PreConditionFailure("this.isDisposed() cannot be true."));

                            test.assertEqual(pixelSize, window.getSize());
                        }
                    }
                });

                final Action1<Integer> setWidthTest = (Integer pixelWidth) ->
                {
                    runner.test("with " + pixelWidth, (Test test) ->
                    {
                        try (final SwingUI ui = SwingUI.create(mainAsyncRunner))
                        {
                            try (final SwingUIWindow window = SwingUIWindow.create(ui))
                            {
                                final int pixelHeight = window.getHeight();

                                final SwingUIWindow setWidthResult = window.setWidth(pixelWidth);
                                test.assertSame(window, setWidthResult);

                                test.assertEqual(pixelWidth, window.getWidth());
                                test.assertEqual(pixelHeight, window.getHeight());
                            }
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
                        try (final SwingUI ui = SwingUI.create(mainAsyncRunner))
                        {
                            try (final SwingUIWindow window = SwingUIWindow.create(ui))
                            {
                                final Size2Integer pixelSize = window.getSize();

                                test.assertThrows(() -> window.setWidth(width),
                                    expected);

                                test.assertEqual(pixelSize, window.getSize());
                            }
                        }
                    });
                };

                setWidthErrorTest.run(null, new PreConditionFailure("width cannot be null."));
                setWidthErrorTest.run(Distance.inches(-1), new PreConditionFailure("width (-1.0 Inches) must be greater than or equal to 0.0 Inches."));

                runner.test("when disposed", (Test test) ->
                {
                    try (final SwingUI ui = SwingUI.create(mainAsyncRunner))
                    {
                        try (final SwingUIWindow window = SwingUIWindow.create(ui))
                        {
                            window.dispose().await();

                            final Size2Integer pixelSize = window.getSize();

                            test.assertThrows(() -> window.setWidth(Distance.inches(2)),
                                new PreConditionFailure("this.isDisposed() cannot be true."));

                            test.assertEqual(pixelSize, window.getSize());
                        }
                    }
                });

                final Action2<Distance,Iterable<Integer>> setWidthTest = (Distance width, Iterable<Integer> possibleWidths) ->
                {
                    runner.test("with " + width, (Test test) ->
                    {
                        try (final SwingUI ui = SwingUI.create(mainAsyncRunner))
                        {
                            try (final SwingUIWindow window = SwingUIWindow.create(ui))
                            {
                                final int pixelHeight = window.getHeight();

                                final SwingUIWindow setWidthResult = window.setWidth(width);
                                test.assertSame(window, setWidthResult);

                                test.assertOneOf(possibleWidths, window.getWidth());
                                test.assertEqual(pixelHeight, window.getHeight());
                            }
                        }
                    });
                };

                setWidthTest.run(Distance.inches(2), Iterable.create(240, 384));
                setWidthTest.run(Distance.centimeters(4), Iterable.create(188, 302));
            });

            runner.testGroup("setHeight(int)", () ->
            {
                final Action2<Integer,Throwable> setHeightErrorTest = (Integer pixelHeight, Throwable expected) ->
                {
                    runner.test("with " + pixelHeight, (Test test) ->
                    {
                        try (final SwingUI ui = SwingUI.create(mainAsyncRunner))
                        {
                            try (final SwingUIWindow window = SwingUIWindow.create(ui))
                            {
                                final Size2Integer pixelSize = window.getSize();

                                test.assertThrows(() -> window.setHeight(pixelHeight),
                                    expected);

                                test.assertEqual(pixelSize, window.getSize());
                            }
                        }
                    });
                };

                setHeightErrorTest.run(-1, new PreConditionFailure("pixelHeight (-1) must be greater than or equal to 0."));

                runner.test("when disposed", (Test test) ->
                {
                    try (final SwingUI ui = SwingUI.create(mainAsyncRunner))
                    {
                        try (final SwingUIWindow window = SwingUIWindow.create(ui))
                        {
                            window.dispose().await();

                            final Size2Integer pixelSize = window.getSize();

                            test.assertThrows(() -> window.setHeight(100),
                                new PreConditionFailure("this.isDisposed() cannot be true."));

                            test.assertEqual(pixelSize, window.getSize());
                        }
                    }
                });

                final Action1<Integer> setHeightTest = (Integer pixelHeight) ->
                {
                    runner.test("with " + pixelHeight, (Test test) ->
                    {
                        try (final SwingUI ui = SwingUI.create(mainAsyncRunner))
                        {
                            try (final SwingUIWindow window = SwingUIWindow.create(ui))
                            {
                                final int pixelWidth = window.getWidth();

                                final SwingUIWindow setHeightResult = window.setHeight(pixelHeight);
                                test.assertSame(window, setHeightResult);

                                test.assertEqual(pixelWidth, window.getWidth());
                                test.assertEqual(pixelHeight, window.getHeight());
                            }
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
                        try (final SwingUI ui = SwingUI.create(mainAsyncRunner))
                        {
                            try (final SwingUIWindow window = SwingUIWindow.create(ui))
                            {
                                final Size2Integer pixelSize = window.getSize();

                                test.assertThrows(() -> window.setHeight(height),
                                    expected);

                                test.assertEqual(pixelSize, window.getSize());
                            }
                        }
                    });
                };

                setHeightErrorTest.run(null, new PreConditionFailure("height cannot be null."));
                setHeightErrorTest.run(Distance.inches(-1), new PreConditionFailure("height (-1.0 Inches) must be greater than or equal to 0.0 Inches."));

                runner.test("when disposed", (Test test) ->
                {
                    try (final SwingUI ui = SwingUI.create(mainAsyncRunner))
                    {
                        try (final SwingUIWindow window = SwingUIWindow.create(ui))
                        {
                            window.dispose().await();

                            final Size2Integer pixelSize = window.getSize();

                            test.assertThrows(() -> window.setHeight(Distance.inches(2)),
                                new PreConditionFailure("this.isDisposed() cannot be true."));

                            test.assertEqual(pixelSize, window.getSize());
                        }
                    }
                });

                final Action2<Distance,Iterable<Integer>> setHeightTest = (Distance height, Iterable<Integer> possibleHeights) ->
                {
                    runner.test("with " + height, (Test test) ->
                    {
                        try (final SwingUI ui = SwingUI.create(mainAsyncRunner))
                        {
                            try (final SwingUIWindow window = SwingUIWindow.create(ui))
                            {
                                final int pixelWidth = window.getWidth();

                                final SwingUIWindow setHeightResult = window.setHeight(height);
                                test.assertSame(window, setHeightResult);

                                test.assertEqual(pixelWidth, window.getWidth());
                                test.assertOneOf(possibleHeights, window.getHeight());
                            }
                        }
                    });
                };

                setHeightTest.run(Distance.inches(2), Iterable.create(240, 384));
                setHeightTest.run(Distance.centimeters(4), Iterable.create(188, 302));
            });

            runner.testGroup("getContent()", () ->
            {
                runner.test("before content has been set", (Test test) ->
                {
                    try (final SwingUI ui = SwingUI.create(mainAsyncRunner))
                    {
                        try (final SwingUIWindow window = SwingUIWindow.create(ui))
                        {
                            test.assertThrows(() -> window.getContent().await(),
                                new NotFoundException("No UIElement content has been set in this UIWindow."));
                        }
                    }
                });
            });

            runner.testGroup("setContent(UIElement)", () ->
            {
                runner.test("with JComponentUIElement", (Test test) ->
                {
                    try (final SwingUI ui = SwingUI.create(mainAsyncRunner))
                    {
                        try (final SwingUIWindow window = SwingUIWindow.create(ui))
                        {
                            final SwingUIText uiText = ui.create(SwingUIText.class).await();
                            final SwingUIWindow setContentResult = window.setContent(uiText);
                            test.assertSame(window, setContentResult);
                            test.assertSame(uiText, window.getContent().await());
                        }
                    }
                });
            });

            runner.test("sandbox", runner.skip(false), (Test test) ->
            {
                try (final SwingUI ui = SwingUI.create(mainAsyncRunner);
                     final SwingUIWindow window = SwingUIWindow.create(ui))
                {
                    window.setWidth(Distance.inches(3));
                    window.setHeight(Distance.inches(3));

                    final UIVerticalLayout verticalLayout = ui.createUIVerticalLayout().await();

                    final UIHorizontalLayout firstNameLayout = ui.createUIHorizontalLayout().await();
                    firstNameLayout.add(ui.createUIText().await().setText("First Name:"));
                    final UITextBox firstNameTextBox = ui.createUITextBox().await();
                    firstNameLayout.add(firstNameTextBox);
                    verticalLayout.add(firstNameLayout);

                    final UIHorizontalLayout lastNameLayout = ui.createUIHorizontalLayout().await();
                    lastNameLayout.add(ui.createUIText().await().setText("Last Name:"));
                    final UITextBox lastNameTextBox = ui.createUITextBox().await();
                    lastNameLayout.add(lastNameTextBox);
                    verticalLayout.add(lastNameLayout);

                    final UIText greeting = ui.createUIText().await();
                    final Action0 updateGreetingText = () ->
                    {
                        final String firstName = firstNameTextBox.getText();
                        final String lastName = lastNameTextBox.getText();
                        if (Strings.isNullOrEmpty(firstName) && Strings.isNullOrEmpty(lastName))
                        {
                            greeting.setText("");
                        }
                        else
                        {
                            String fullName = firstName;
                            if (!Strings.isNullOrEmpty(firstName) && !Strings.isNullOrEmpty(lastName))
                            {
                                fullName += ' ';
                            }
                            fullName += lastName;
                            greeting.setText("Hello, " + fullName + "!");
                        }
                    };
                    firstNameTextBox.onTextChanged(updateGreetingText);
                    lastNameTextBox.onTextChanged(updateGreetingText);
                    verticalLayout.add(greeting);

                    window.setContent(verticalLayout);

                    window.setVisible(true);
                    window.await();
                }
            });
        });
    }
}
