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
                setBackgroundColorTest.run(Color.create(1, 2, 3));
                setBackgroundColorTest.run(Color.create(1, 2, 3, Color.ComponentMax));
            });

            runner.testGroup("setWidth(int)", () ->
            {
                final Action2<Integer,Throwable> setWidthErrorTest = (Integer width, Throwable expected) ->
                {
                    runner.test("with " + width, (Test test) ->
                    {
                        final UIElement uiElement = creator.run();
                        final int originalWidth = uiElement.getWidth();

                        test.assertThrows(() -> uiElement.setWidth(width),
                            expected);

                        test.assertEqual(originalWidth, uiElement.getWidth());
                    });
                };

                setWidthErrorTest.run(-10, new PreConditionFailure("width (-10) must be greater than or equal to 0."));
                setWidthErrorTest.run(-1, new PreConditionFailure("width (-1) must be greater than or equal to 0."));

                final Action1<Integer> setWidthTest = (Integer width) ->
                {
                    runner.test("with " + width, (Test test) ->
                    {
                        final UIElement uiElement = creator.run();
                        final UIElement setWidthResult = uiElement.setWidth(width);
                        test.assertSame(uiElement, setWidthResult);
                        test.assertEqual(width, uiElement.getWidth());
                    });
                };

                setWidthTest.run(0);
                setWidthTest.run(1);
                setWidthTest.run(100);

                runner.test("after having a dynamic size",
                    (TestResources resources) -> Tuple.create(resources.getSynchronization()),
                    (Test test, Synchronization synchronization) ->
                {
                    final Size2Integer size1 = Size2.create(100, 200);
                    final MutableDynamicSize2Integer dynamicSize = DynamicSize2Integer.create(size1);
                    final Gate gate = synchronization.createGate(false);
                    final UIElement uiElement = creator.run()
                        .setDynamicSize(dynamicSize);
                    try (final Disposable subscription = uiElement.onSizeChanged(gate::open))
                    {
                        test.assertEqual(size1, uiElement.getSize());

                        final Size2Integer size2 = Size2.create(dynamicSize.getWidth() + 1, dynamicSize.getHeight() + 1);
                        dynamicSize.set(size2);
                        gate.passThrough().await();
                        gate.close();

                        test.assertEqual(size2, uiElement.getSize());

                        uiElement.setWidth(100);

                        final Size2Integer size4 = Size2.create(dynamicSize.getWidth() + 1, dynamicSize.getHeight() + 1);
                        dynamicSize.set(size4);

                        test.assertEqual(Size2.create(100, size2.getHeight()), uiElement.getSize());
                        test.assertEqual(size4, dynamicSize);
                    }
                });
            });

            runner.testGroup("setWidth(Distance)", () ->
            {
                final Action2<Distance,Throwable> setWidthErrorTest = (Distance width, Throwable expected) ->
                {
                    runner.test("with " + width, (Test test) ->
                    {
                        final UIElement uiElement = creator.run();
                        final int originalWidth = uiElement.getWidth();

                        test.assertThrows(() -> uiElement.setWidth(width),
                            expected);

                        test.assertEqual(originalWidth, uiElement.getWidth());
                    });
                };

                setWidthErrorTest.run(Distance.inches(-10), new PreConditionFailure("width (-10.0 Inches) must be greater than or equal to 0.0 Inches."));
                setWidthErrorTest.run(Distance.millimeters(-1), new PreConditionFailure("width (-1.0 Millimeters) must be greater than or equal to 0.0 Inches."));

                final Action2<Distance,Integer> setWidthTest = (Distance width, Integer expected) ->
                {
                    runner.test("with " + width, (Test test) ->
                    {
                        final UIElement uiElement = creator.run();

                        final UIElement setWidthResult = uiElement.setWidth(width);
                        test.assertSame(uiElement, setWidthResult);
                        test.assertEqual(expected, uiElement.getWidth());
                    });
                };

                setWidthTest.run(Distance.zero, 0);
                setWidthTest.run(Distance.millimeters(1), 3);
                setWidthTest.run(Distance.millimeters(10), 39);
                setWidthTest.run(Distance.inches(1), 100);

                runner.test("after having a dynamic size",
                    (TestResources resources) -> Tuple.create(resources.getSynchronization()),
                    (Test test, Synchronization synchronization) ->
                {
                    final Size2Integer size1 = Size2.create(100, 200);
                    final MutableDynamicSize2Integer dynamicSize = DynamicSize2Integer.create(size1);
                    final Gate gate = synchronization.createGate(false);
                    final UIElement uiElement = creator.run()
                        .setDynamicSize(dynamicSize);
                    try (final Disposable subscription = uiElement.onSizeChanged(gate::open))
                    {
                        test.assertEqual(size1, uiElement.getSize());

                        final Size2Integer size2 = Size2.create(dynamicSize.getWidth() + 1, dynamicSize.getHeight() + 1);
                        dynamicSize.set(size2);
                        gate.passThrough().await();
                        gate.close();

                        test.assertEqual(size2, uiElement.getSize());

                        uiElement.setWidth(Distance.inches(1));

                        final Size2Integer size4 = Size2.create(dynamicSize.getWidth() + 1, dynamicSize.getHeight() + 1);
                        dynamicSize.set(size4);

                        test.assertEqual(Size2.create(100, size2.getHeight()), uiElement.getSize());
                        test.assertEqual(size4, dynamicSize);
                    }
                });
            });

            runner.testGroup("setHeight(int)", () ->
            {
                final Action2<Integer,Throwable> setHeightErrorTest = (Integer height, Throwable expected) ->
                {
                    runner.test("with " + height, (Test test) ->
                    {
                        final UIElement uiElement = creator.run();
                        final int originalHeight = uiElement.getHeight();

                        test.assertThrows(() -> uiElement.setHeight(height),
                            expected);

                        test.assertEqual(originalHeight, uiElement.getHeight());
                    });
                };

                setHeightErrorTest.run(-10, new PreConditionFailure("height (-10) must be greater than or equal to 0."));
                setHeightErrorTest.run(-1, new PreConditionFailure("height (-1) must be greater than or equal to 0."));

                final Action1<Integer> setHeightTest = (Integer height) ->
                {
                    runner.test("with " + height, (Test test) ->
                    {
                        final UIElement uiElement = creator.run();
                        final UIElement setHeightResult = uiElement.setHeight(height);
                        test.assertSame(uiElement, setHeightResult);
                        test.assertEqual(height, uiElement.getHeight());
                    });
                };

                setHeightTest.run(0);
                setHeightTest.run(1);
                setHeightTest.run(100);

                runner.test("after having a dynamic size",
                    (TestResources resources) -> Tuple.create(resources.getSynchronization()),
                    (Test test, Synchronization synchronization) ->
                {
                    final Size2Integer size1 = Size2.create(100, 200);
                    final MutableDynamicSize2Integer dynamicSize = DynamicSize2Integer.create(size1);
                    final Gate gate = synchronization.createGate(false);
                    final UIElement uiElement = creator.run()
                        .setDynamicSize(dynamicSize);
                    try (final Disposable subscription = uiElement.onSizeChanged(gate::open))
                    {
                        test.assertEqual(size1, uiElement.getSize());

                        final Size2Integer size2 = Size2.create(dynamicSize.getWidth() + 1, dynamicSize.getHeight() + 1);
                        dynamicSize.set(size2);
                        gate.passThrough().await();
                        gate.close();

                        test.assertEqual(size2, uiElement.getSize());

                        uiElement.setHeight(200);

                        final Size2Integer size3 = Size2.create(dynamicSize.getWidth() + 1, dynamicSize.getHeight() + 1);
                        dynamicSize.set(size3);

                        test.assertEqual(Size2.create(size2.getWidth(), 200), uiElement.getSize());
                        test.assertEqual(size3, dynamicSize);
                    }
                });
            });

            runner.testGroup("setHeight(Distance)", () ->
            {
                final Action2<Distance,Throwable> setHeightErrorTest = (Distance height, Throwable expected) ->
                {
                    runner.test("with " + height, (Test test) ->
                    {
                        final UIElement uiElement = creator.run();
                        final int originalHeight = uiElement.getHeight();

                        test.assertThrows(() -> uiElement.setHeight(height),
                            expected);

                        test.assertEqual(originalHeight, uiElement.getHeight());
                    });
                };

                setHeightErrorTest.run(Distance.inches(-10), new PreConditionFailure("height (-10.0 Inches) must be greater than or equal to 0.0 Inches."));
                setHeightErrorTest.run(Distance.millimeters(-1), new PreConditionFailure("height (-1.0 Millimeters) must be greater than or equal to 0.0 Inches."));

                final Action2<Distance,Integer> setHeightTest = (Distance height, Integer expected) ->
                {
                    runner.test("with " + height, (Test test) ->
                    {
                        final UIElement uiElement = creator.run();

                        final UIElement setHeightResult = uiElement.setHeight(height);
                        test.assertSame(uiElement, setHeightResult);
                        test.assertEqual(expected, uiElement.getHeight());
                    });
                };

                setHeightTest.run(Distance.zero, 0);
                setHeightTest.run(Distance.millimeters(1), 7);
                setHeightTest.run(Distance.millimeters(10), 78);
                setHeightTest.run(Distance.inches(1), 200);

                runner.test("after having a dynamic size",
                    (TestResources resources) -> Tuple.create(resources.getSynchronization()),
                    (Test test, Synchronization synchronization) ->
                {
                    final Size2Integer size1 = Size2.create(100, 200);
                    final MutableDynamicSize2Integer dynamicSize = DynamicSize2Integer.create(size1);
                    final Gate gate = synchronization.createGate(false);
                    final UIElement uiElement = creator.run()
                        .setDynamicSize(dynamicSize);
                    try (final Disposable subscription = uiElement.onSizeChanged(gate::open))
                    {
                        test.assertEqual(size1, uiElement.getSize());

                        final Size2Integer size2 = Size2.create(dynamicSize.getWidth() + 1, dynamicSize.getHeight() + 1);
                        dynamicSize.set(size2);
                        gate.passThrough().await();
                        gate.close();

                        test.assertEqual(size2, uiElement.getSize());

                        uiElement.setHeight(Distance.inches(1));

                        final Size2Integer size3 = Size2.create(dynamicSize.getWidth() + 1, dynamicSize.getHeight() + 1);
                        dynamicSize.set(size3);

                        test.assertEqual(Size2.create(size2.getWidth(), 200), uiElement.getSize());
                        test.assertEqual(size3, dynamicSize);
                    }
                });
            });

            runner.testGroup("setSize(int,int)", () ->
            {
                final Action3<Integer,Integer,Throwable> setSizeErrorTest = (Integer width, Integer height, Throwable expected) ->
                {
                    runner.test("with " + English.andList(width, height), (Test test) ->
                    {
                        final UIElement uiElement = creator.run();
                        final Size2Integer size = uiElement.getSize();

                        test.assertThrows(() -> uiElement.setSize(width, height),
                            expected);

                        test.assertEqual(size, uiElement.getSize());
                    });
                };

                setSizeErrorTest.run(-10, 10, new PreConditionFailure("width (-10) must be greater than or equal to 0."));
                setSizeErrorTest.run(-1, 10, new PreConditionFailure("width (-1) must be greater than or equal to 0."));
                setSizeErrorTest.run(1, -10, new PreConditionFailure("height (-10) must be greater than or equal to 0."));
                setSizeErrorTest.run(1, -1, new PreConditionFailure("height (-1) must be greater than or equal to 0."));

                final Action2<Integer,Integer> setSizeTest = (Integer width, Integer height) ->
                {
                    runner.test("with " + English.andList(width, height), (Test test) ->
                    {
                        final UIElement uiElement = creator.run();
                        final UIElement setSizeResult = uiElement.setSize(width, height);
                        test.assertSame(uiElement, setSizeResult);
                        test.assertEqual(width, uiElement.getWidth());
                        test.assertEqual(height, uiElement.getHeight());
                        test.assertEqual(Size2Integer.create(width, height), uiElement.getSize());
                    });
                };

                setSizeTest.run(0, 0);
                setSizeTest.run(0, 1);
                setSizeTest.run(0, 10);
                setSizeTest.run(1, 0);
                setSizeTest.run(1, 1);
                setSizeTest.run(1, 10);
                setSizeTest.run(10, 0);
                setSizeTest.run(10, 1);
                setSizeTest.run(10, 10);

                runner.test("after having a dynamic size",
                    (TestResources resources) -> Tuple.create(resources.getSynchronization()),
                    (Test test, Synchronization synchronization) ->
                {
                    final Size2Integer size1 = Size2.create(100, 200);
                    final MutableDynamicSize2Integer dynamicSize = DynamicSize2Integer.create(size1);
                    final Gate gate = synchronization.createGate(false);
                    final UIElement uiElement = creator.run()
                        .setDynamicSize(dynamicSize);
                    try (final Disposable subscription = uiElement.onSizeChanged(gate::open))
                    {
                        test.assertEqual(size1, uiElement.getSize());

                        final Size2Integer size2 = Size2.create(dynamicSize.getWidth() + 1, dynamicSize.getHeight() + 1);
                        dynamicSize.set(size2);
                        gate.passThrough().await();
                        gate.close();

                        test.assertEqual(size2, uiElement.getSize());

                        final Size2Integer size3 = Size2.create(dynamicSize.getWidth() + 1, dynamicSize.getHeight() + 1);
                        uiElement.setSize(size3);

                        final Size2Integer size4 = Size2.create(dynamicSize.getWidth() + 1, dynamicSize.getHeight() + 1);
                        dynamicSize.set(size4);

                        test.assertEqual(size3, uiElement.getSize());
                        test.assertEqual(size4, dynamicSize);
                    }
                });
            });

            runner.testGroup("setSize(Size2Integer)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final UIElement uiElement = creator.run();
                    final Size2Integer size = uiElement.getSize();

                    test.assertThrows(() -> uiElement.setSize((Size2Integer)null),
                        new PreConditionFailure("size cannot be null."));

                    test.assertEqual(size, uiElement.getSize());
                });

                final Action1<Size2Integer> setSizeTest = (Size2Integer size) ->
                {
                    runner.test("with " + size, (Test test) ->
                    {
                        final UIElement uiElement = creator.run();
                        final UIElement setSizeResult = uiElement.setSize(size);
                        test.assertSame(uiElement, setSizeResult);
                        test.assertEqual(size.getWidthAsInt(), uiElement.getWidth());
                        test.assertEqual(size.getHeightAsInt(), uiElement.getHeight());
                        test.assertEqual(size, uiElement.getSize());
                    });
                };

                setSizeTest.run(Size2.create(0, 0));
                setSizeTest.run(Size2.create(0, 1));
                setSizeTest.run(Size2.create(0, 10));
                setSizeTest.run(Size2.create(1, 0));
                setSizeTest.run(Size2.create(1, 1));
                setSizeTest.run(Size2.create(1, 10));
                setSizeTest.run(Size2.create(10, 0));
                setSizeTest.run(Size2.create(10, 1));
                setSizeTest.run(Size2.create(10, 10));

                runner.test("after having a dynamic size",
                    (TestResources resources) -> Tuple.create(resources.getSynchronization()),
                    (Test test, Synchronization synchronization) ->
                {
                    final Size2Integer size1 = Size2.create(100, 200);
                    final MutableDynamicSize2Integer dynamicSize = DynamicSize2Integer.create(size1);
                    final Gate gate = synchronization.createGate(false);
                    final UIElement uiElement = creator.run()
                        .setDynamicSize(dynamicSize);
                    try (final Disposable subscription = uiElement.onSizeChanged(gate::open))
                    {
                        test.assertEqual(size1, uiElement.getSize());

                        final Size2Integer size2 = Size2.create(dynamicSize.getWidth() + 1, dynamicSize.getHeight() + 1);
                        dynamicSize.set(size2);
                        gate.passThrough().await();
                        gate.close();

                        test.assertEqual(size2, uiElement.getSize());

                        final Size2Integer size3 = Size2.create(dynamicSize.getWidth() + 1, dynamicSize.getHeight() + 1);
                        uiElement.setSize(size3);

                        final Size2Integer size4 = Size2.create(dynamicSize.getWidth() + 1, dynamicSize.getHeight() + 1);
                        dynamicSize.set(size4);

                        test.assertEqual(size3, uiElement.getSize());
                        test.assertEqual(size4, dynamicSize);
                    }
                });
            });

            runner.testGroup("setSize(Distance,Distance)", () ->
            {
                final Action3<Distance,Distance,Throwable> setSizeErrorTest = (Distance width, Distance height, Throwable expected) ->
                {
                    runner.test("with " + English.andList(width, height), (Test test) ->
                    {
                        final UIElement uiElement = creator.run();
                        final Size2Integer size = uiElement.getSize();

                        test.assertThrows(() -> uiElement.setSize(width, height),
                            expected);

                        test.assertEqual(size, uiElement.getSize());
                    });
                };

                setSizeErrorTest.run(Distance.inches(-10), Distance.inches(10), new PreConditionFailure("width (-10.0 Inches) must be greater than or equal to 0.0 Inches."));
                setSizeErrorTest.run(Distance.inches(-1), Distance.inches(10), new PreConditionFailure("width (-1.0 Inches) must be greater than or equal to 0.0 Inches."));
                setSizeErrorTest.run(Distance.inches(1), Distance.inches(-10), new PreConditionFailure("height (-10.0 Inches) must be greater than or equal to 0.0 Inches."));
                setSizeErrorTest.run(Distance.inches(1), Distance.inches(-1), new PreConditionFailure("height (-1.0 Inches) must be greater than or equal to 0.0 Inches."));

                final Action3<Distance,Distance,Size2Integer> setSizeTest = (Distance width, Distance height, Size2Integer expected) ->
                {
                    runner.test("with " + English.andList(width, height), (Test test) ->
                    {
                        final UIElement uiElement = creator.run();
                        final UIElement setSizeResult = uiElement.setSize(width, height);
                        test.assertSame(uiElement, setSizeResult);
                        test.assertEqual(expected.getWidth(), uiElement.getWidth());
                        test.assertEqual(expected.getHeight(), uiElement.getHeight());
                        test.assertEqual(expected, uiElement.getSize());
                    });
                };

                setSizeTest.run(Distance.zero, Distance.zero, Size2.create(0, 0));
                setSizeTest.run(Distance.zero, Distance.inches(1), Size2.create(0, 200));
                setSizeTest.run(Distance.zero, Distance.inches(10), Size2.create(0, 2000));
                setSizeTest.run(Distance.inches(1), Distance.zero, Size2.create(100, 0));
                setSizeTest.run(Distance.inches(1), Distance.inches(1), Size2.create(100, 200));
                setSizeTest.run(Distance.inches(1), Distance.inches(10), Size2.create(100, 2000));
                setSizeTest.run(Distance.inches(10), Distance.zero, Size2.create(1000, 0));
                setSizeTest.run(Distance.inches(10), Distance.inches(1), Size2.create(1000, 200));
                setSizeTest.run(Distance.inches(10), Distance.inches(10), Size2.create(1000, 2000));

                runner.test("after having a dynamic size",
                    (TestResources resources) -> Tuple.create(resources.getSynchronization()),
                    (Test test, Synchronization synchronization) ->
                {
                    final Size2Integer size1 = Size2.create(100, 200);
                    final MutableDynamicSize2Integer dynamicSize = DynamicSize2Integer.create(size1);
                    final Gate gate = synchronization.createGate(false);
                    final UIElement uiElement = creator.run()
                        .setDynamicSize(dynamicSize);
                    try (final Disposable subscription = uiElement.onSizeChanged(gate::open))
                    {
                        test.assertEqual(size1, uiElement.getSize());

                        final Size2Integer size2 = Size2.create(dynamicSize.getWidth() + 1, dynamicSize.getHeight() + 1);
                        dynamicSize.set(size2);
                        gate.passThrough().await();
                        gate.close();

                        test.assertEqual(size2, uiElement.getSize());

                        uiElement.setSize(Distance.inches(5), Distance.inches(7));

                        final Size2Integer size3 = Size2.create(dynamicSize.getWidth() + 1, dynamicSize.getHeight() + 1);
                        dynamicSize.set(size3);

                        test.assertEqual(Size2.create(500, 1400), uiElement.getSize());
                        test.assertEqual(size3, dynamicSize);
                    }
                });
            });

            runner.test("getDynamicSize()",
                (TestResources resources) -> Tuple.create(resources.getSynchronization()),
                (Test test, Synchronization synchronization) ->
            {
                final UIElement uiElement = creator.run();
                final Size2Integer size1 = uiElement.getSize();

                final DynamicSize2Integer dynamicSize = uiElement.getDynamicSize();
                test.assertNotNull(dynamicSize);
                test.assertEqual(uiElement.getSize(), dynamicSize);

                uiElement.setSize(uiElement.getWidth() + 1, uiElement.getHeight() + 1);
                final Size2Integer size2 = uiElement.getSize();
                test.assertNotEqual(size1, size2);
                test.assertEqual(size2, dynamicSize);

                final Gate gate = synchronization.createGate(false);

                final List<SizeChange> sizeChanges = List.create();
                try (final Disposable subscription = dynamicSize.onChanged((SizeChange sizeChange) ->
                    {
                        sizeChanges.add(sizeChange);
                        gate.open();
                    }))
                {
                    test.assertEqual(Iterable.create(), sizeChanges);

                    uiElement.setSize(uiElement.getWidth() + 1, uiElement.getHeight() + 1);
                    gate.passThrough().await();
                    gate.close();
                    final Size2Integer size3 = uiElement.getSize();
                    test.assertNotEqual(size1, size3);
                    test.assertNotEqual(size2, size3);
                    test.assertEqual(size3, dynamicSize);
                    test.assertEqual(
                        Iterable.create(
                            SizeChange.create()
                                .setPreviousSize(size2)
                                .setNewSize(size3)),
                        sizeChanges);

                    subscription.dispose().await();

                    uiElement.setSize(uiElement.getWidth() + 1, uiElement.getHeight() + 1);
                    final Size2Integer size4 = uiElement.getSize();
                    test.assertNotEqual(size1, size4);
                    test.assertNotEqual(size2, size4);
                    test.assertNotEqual(size3, size4);
                    test.assertEqual(size4, dynamicSize);
                    test.assertEqual(
                        Iterable.create(
                            SizeChange.create()
                                .setPreviousSize(size2)
                                .setNewSize(size3)),
                        sizeChanges);
                }
            });

            runner.testGroup("setDynamicSize(DynamicSize2Integer)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final UIElement uiElement = creator.run();
                    final Size2Integer size = uiElement.getSize();

                    test.assertThrows(() -> uiElement.setDynamicSize(null),
                        new PreConditionFailure("dynamicSize cannot be null."));

                    test.assertEqual(size, uiElement.getSize());
                });

                runner.test("with non-null", (Test test) ->
                {
                    final UIElement uiElement = creator.run();
                    final Size2Integer size1 = uiElement.getSize();

                    final MutableDynamicSize2Integer dynamicSize = DynamicSize2Integer.create(size1.getWidth() + 1, size1.getHeight() + 1);
                    final UIElement setDynamicSizeResult = uiElement.setDynamicSize(dynamicSize);
                    test.assertSame(uiElement, setDynamicSizeResult);
                    test.assertEqual(dynamicSize, uiElement.getSize());

                    final Size2Integer size2 = uiElement.getSize();
                    dynamicSize.set(size2.getWidth() + 1, size2.getHeight() + 2);
                    test.assertEqual(dynamicSize, uiElement.getSize());
                });
            });

            runner.testGroup("onSizeChanged(Action0)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final UIElement uiElement = creator.run();

                    test.assertThrows(() -> uiElement.onSizeChanged((Action0)null),
                        new PreConditionFailure("action cannot be null."));
                });

                runner.test("with non-null",
                    (TestResources resources) -> Tuple.create(resources.getSynchronization()),
                    (Test test, Synchronization synchronization) ->
                {
                    final UIElement uiElement = creator.run();

                    final List<Size2Integer> sizes = List.create();
                    final Gate gate = synchronization.createGate(false);
                    try (final Disposable subscription = uiElement.onSizeChanged(() ->
                    {
                        sizes.add(uiElement.getSize());
                        gate.open();
                    }))
                    {
                        final Size2Integer size1 = Size2.create(uiElement.getWidth() + 1, uiElement.getHeight() + 1);
                        uiElement.setSize(size1);
                        gate.passThrough().await();
                        gate.close();

                        test.assertEqual(Iterable.create(size1), sizes);

                        final Size2Integer size2 = Size2.create(uiElement.getWidth() + 1, uiElement.getHeight() + 1);
                        uiElement.setSize(size2);
                        gate.passThrough().await();
                        gate.close();

                        test.assertEqual(Iterable.create(size1, size2), sizes);

                        subscription.dispose().await();

                        final Size2Integer size3 = Size2.create(uiElement.getWidth() + 1, uiElement.getHeight() + 1);
                        uiElement.setSize(size3);

                        test.assertEqual(Iterable.create(size1, size2), sizes);
                    }
                });
            });

            runner.testGroup("onSizeChanged(Action1<SizeChange>)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final UIElement uiElement = creator.run();

                    test.assertThrows(() -> uiElement.onSizeChanged((Action1<SizeChange>)null),
                        new PreConditionFailure("action cannot be null."));
                });

                runner.test("with non-null",
                    (TestResources resources) -> Tuple.create(resources.getSynchronization()),
                    (Test test, Synchronization synchronization) ->
                {
                    final UIElement uiElement = creator.run();
                    final Size2Integer size0 = uiElement.getSize();

                    final List<SizeChange> sizes = List.create();
                    final Gate gate = synchronization.createGate(false);
                    try (final Disposable subscription = uiElement.onSizeChanged((SizeChange sizeChange) ->
                    {
                        sizes.add(sizeChange);
                        gate.open();
                    }))
                    {
                        final Size2Integer size1 = Size2.create(uiElement.getWidth() + 1, uiElement.getHeight() + 1);
                        uiElement.setSize(size1);
                        gate.passThrough().await();
                        gate.close();

                        test.assertEqual(
                            Iterable.create(
                                SizeChange.create()
                                    .setPreviousSize(size0)
                                    .setNewSize(size1)),
                            sizes);

                        final Size2Integer size2 = Size2.create(uiElement.getWidth() + 1, uiElement.getHeight() + 1);
                        uiElement.setSize(size2);
                        gate.passThrough().await();
                        gate.close();

                        test.assertEqual(
                            Iterable.create(
                                SizeChange.create()
                                    .setPreviousSize(size0)
                                    .setNewSize(size1),
                                SizeChange.create()
                                    .setPreviousSize(size1)
                                    .setNewSize(size2)),
                            sizes);

                        subscription.dispose().await();

                        final Size2Integer size3 = Size2.create(uiElement.getWidth() + 1, uiElement.getHeight() + 1);
                        uiElement.setSize(size3);

                        test.assertEqual(
                            Iterable.create(
                                SizeChange.create()
                                    .setPreviousSize(size0)
                                    .setNewSize(size1),
                                SizeChange.create()
                                    .setPreviousSize(size1)
                                    .setNewSize(size2)),
                            sizes);
                    }
                });
            });
        });
    }
}
