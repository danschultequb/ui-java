package qub;

public interface ScaledDynamicSize2IntegerTests
{
    public static void test(TestRunner runner)
    {
        runner.testGroup(ScaledDynamicSize2Integer.class, () ->
        {
            final Function0<ScaledDynamicSize2Integer> creator = () ->
            {
                final DynamicSize2Integer dynamicSize = DynamicSize2Integer.create(100, 200);
                return ScaledDynamicSize2Integer.create(dynamicSize)
                    .setWidthScale(0.25)
                    .setHeightScale(0.5);
            };

            DynamicSize2IntegerTests.test(runner, creator);

            runner.testGroup("create(DynamicSize2Integer)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    test.assertThrows(() -> ScaledDynamicSize2Integer.create(null),
                        new PreConditionFailure("innerSize cannot be null."));
                });

                runner.test("with non-null", (Test test) ->
                {
                    final MutableDynamicSize2Integer dynamicSize = DynamicSize2Integer.create(1, 2);

                    final ScaledDynamicSize2Integer scaledDynamicSize = ScaledDynamicSize2Integer.create(dynamicSize);
                    test.assertNotNull(scaledDynamicSize);
                    test.assertEqual(dynamicSize, scaledDynamicSize);

                    scaledDynamicSize.setWidthScale(5).setHeightScale(20);
                    test.assertEqual(5, scaledDynamicSize.getWidthAsInt());
                    test.assertEqual(40, scaledDynamicSize.getHeightAsInt());
                });
            });

            runner.testGroup("setWidthScale(double)", () ->
            {
                runner.test("with -1.0", (Test test) ->
                {
                    final ScaledDynamicSize2Integer scaledDynamicSize = creator.run();
                    final double widthScale = scaledDynamicSize.getWidthScale();

                    test.assertThrows(() -> scaledDynamicSize.setWidthScale(-1),
                        new PreConditionFailure("widthScale (-1.0) must be greater than or equal to 0.0."));

                    test.assertEqual(widthScale, scaledDynamicSize.getWidthScale());
                });

                final Action1<Double> setWidthScaleTest = (Double widthScale) ->
                {
                    runner.test("with " + widthScale, (Test test) ->
                    {
                        final ScaledDynamicSize2Integer scaledDynamicSize = creator.run();
                        final ScaledDynamicSize2Integer setWidthScaleResult = scaledDynamicSize.setWidthScale(widthScale);
                        test.assertSame(scaledDynamicSize, setWidthScaleResult);
                        test.assertEqual(widthScale, scaledDynamicSize.getWidthScale());
                    });
                };

                setWidthScaleTest.run(0.0);
                setWidthScaleTest.run(0.5);
                setWidthScaleTest.run(1.0);
                setWidthScaleTest.run(2.0);
            });

            runner.testGroup("setHeightScale(double)", () ->
            {
                runner.test("with -1.0", (Test test) ->
                {
                    final ScaledDynamicSize2Integer scaledDynamicSize = creator.run();
                    final double heightScale = scaledDynamicSize.getHeightScale();

                    test.assertThrows(() -> scaledDynamicSize.setHeightScale(-1),
                        new PreConditionFailure("heightScale (-1.0) must be greater than or equal to 0.0."));

                    test.assertEqual(heightScale, scaledDynamicSize.getHeightScale());
                });

                final Action1<Double> setHeightScaleTest = (Double heightScale) ->
                {
                    runner.test("with " + heightScale, (Test test) ->
                    {
                        final ScaledDynamicSize2Integer scaledDynamicSize = creator.run();
                        final ScaledDynamicSize2Integer setHeightScaleResult = scaledDynamicSize.setHeightScale(heightScale);
                        test.assertSame(scaledDynamicSize, setHeightScaleResult);
                        test.assertEqual(heightScale, scaledDynamicSize.getHeightScale());
                    });
                };

                setHeightScaleTest.run(0.0);
                setHeightScaleTest.run(0.5);
                setHeightScaleTest.run(1.0);
                setHeightScaleTest.run(2.0);
            });

            runner.testGroup("setScale(double)", () ->
            {
                runner.test("with -1.0", (Test test) ->
                {
                    final ScaledDynamicSize2Integer scaledDynamicSize = creator.run();
                    final double widthScale = scaledDynamicSize.getWidthScale();
                    final double heightScale = scaledDynamicSize.getHeightScale();

                    test.assertThrows(() -> scaledDynamicSize.setScale(-1),
                        new PreConditionFailure("scale (-1.0) must be greater than or equal to 0.0."));

                    test.assertEqual(widthScale, scaledDynamicSize.getWidthScale());
                    test.assertEqual(heightScale, scaledDynamicSize.getHeightScale());
                });

                final Action1<Double> setScaleTest = (Double scale) ->
                {
                    runner.test("with " + scale, (Test test) ->
                    {
                        final ScaledDynamicSize2Integer scaledDynamicSize = creator.run();
                        final ScaledDynamicSize2Integer setScaleResult = scaledDynamicSize.setScale(scale);
                        test.assertSame(scaledDynamicSize, setScaleResult);
                        test.assertEqual(scale, scaledDynamicSize.getWidthScale());
                        test.assertEqual(scale, scaledDynamicSize.getHeightScale());
                    });
                };

                setScaleTest.run(0.0);
                setScaleTest.run(0.5);
                setScaleTest.run(1.0);
                setScaleTest.run(2.0);
            });

            runner.testGroup("setScale(double,double)", () ->
            {
                final Action3<Double,Double,Throwable> setScaleErrorTest = (Double widthScale, Double heightScale, Throwable expected) ->
                {
                    runner.test("with " + English.andList(widthScale, heightScale), (Test test) ->
                    {
                        final ScaledDynamicSize2Integer scaledDynamicSize = creator.run();
                        final double previousWidthScale = scaledDynamicSize.getWidthScale();
                        final double previousHeightScale = scaledDynamicSize.getHeightScale();

                        test.assertThrows(() -> scaledDynamicSize.setScale(widthScale, heightScale),
                            expected);

                        test.assertEqual(previousWidthScale, scaledDynamicSize.getWidthScale());
                        test.assertEqual(previousHeightScale, scaledDynamicSize.getHeightScale());
                    });
                };

                setScaleErrorTest.run(-1.0, 0.0, new PreConditionFailure("widthScale (-1.0) must be greater than or equal to 0.0."));
                setScaleErrorTest.run(0.0, -1.0, new PreConditionFailure("heightScale (-1.0) must be greater than or equal to 0.0."));

                final Action2<Double,Double> setScaleTest = (Double widthScale, Double heightScale) ->
                {
                    runner.test("with " + English.andList(widthScale, heightScale), (Test test) ->
                    {
                        final ScaledDynamicSize2Integer scaledDynamicSize = creator.run();
                        final ScaledDynamicSize2Integer setScaleResult = scaledDynamicSize.setScale(widthScale, heightScale);
                        test.assertSame(scaledDynamicSize, setScaleResult);
                        test.assertEqual(widthScale, scaledDynamicSize.getWidthScale());
                        test.assertEqual(heightScale, scaledDynamicSize.getHeightScale());
                    });
                };

                setScaleTest.run(0.0, 0.0);
                setScaleTest.run(0.0, 0.5);
                setScaleTest.run(0.0, 1.0);
                setScaleTest.run(0.0, 2.0);
                setScaleTest.run(0.5, 0.0);
                setScaleTest.run(0.5, 0.5);
                setScaleTest.run(0.5, 1.0);
                setScaleTest.run(0.5, 2.0);
                setScaleTest.run(1.0, 0.0);
                setScaleTest.run(1.0, 0.5);
                setScaleTest.run(1.0, 1.0);
                setScaleTest.run(1.0, 2.0);
                setScaleTest.run(2.0, 0.0);
                setScaleTest.run(2.0, 0.5);
                setScaleTest.run(2.0, 1.0);
                setScaleTest.run(2.0, 2.0);
            });

            runner.testGroup("getWidth()", () ->
            {
                final Action3<Integer,Double,Integer> getWidthTest = (Integer innerWidth, Double widthScale, Integer expected) ->
                {
                    runner.test("with " + English.andList(innerWidth, widthScale), (Test test) ->
                    {
                        final DynamicSize2Integer dynamicSize = DynamicSize2Integer.create()
                            .setWidth(innerWidth);
                        final ScaledDynamicSize2Integer scaledDynamicSize = ScaledDynamicSize2Integer.create(dynamicSize)
                            .setWidthScale(widthScale);
                        test.assertEqual(expected, scaledDynamicSize.getWidth());
                    });
                };

                getWidthTest.run(0, 0.0, 0);
                getWidthTest.run(0, 0.5, 0);
                getWidthTest.run(0, 1.0, 0);
                getWidthTest.run(0, 2.0, 0);
                getWidthTest.run(1, 0.0, 0);
                getWidthTest.run(1, 0.5, 0);
                getWidthTest.run(1, 1.0, 1);
                getWidthTest.run(1, 2.0, 2);
                getWidthTest.run(5, 0.0, 0);
                getWidthTest.run(5, 0.5, 2);
                getWidthTest.run(5, 1.0, 5);
                getWidthTest.run(5, 2.0, 10);
            });

            runner.testGroup("getHeight()", () ->
            {
                final Action3<Integer,Double,Integer> getHeightTest = (Integer innerHeight, Double heightScale, Integer expected) ->
                {
                    runner.test("with " + English.andList(innerHeight, heightScale), (Test test) ->
                    {
                        final DynamicSize2Integer dynamicSize = DynamicSize2Integer.create()
                            .setHeight(innerHeight);
                        final ScaledDynamicSize2Integer scaledDynamicSize = ScaledDynamicSize2Integer.create(dynamicSize)
                            .setHeightScale(heightScale);
                        test.assertEqual(expected, scaledDynamicSize.getHeight());
                    });
                };

                getHeightTest.run(0, 0.0, 0);
                getHeightTest.run(0, 0.5, 0);
                getHeightTest.run(0, 1.0, 0);
                getHeightTest.run(0, 2.0, 0);
                getHeightTest.run(1, 0.0, 0);
                getHeightTest.run(1, 0.5, 0);
                getHeightTest.run(1, 1.0, 1);
                getHeightTest.run(1, 2.0, 2);
                getHeightTest.run(5, 0.0, 0);
                getHeightTest.run(5, 0.5, 2);
                getHeightTest.run(5, 1.0, 5);
                getHeightTest.run(5, 2.0, 10);
            });

            runner.testGroup("onChanged(Action0)", () ->
            {
                runner.test("when innerSize changes but scaled size doesn't change", (Test test) ->
                {
                    final MutableDynamicSize2Integer dynamicSize = DynamicSize2Integer.create()
                        .setWidth(100);
                    final ScaledDynamicSize2Integer scaledDynamicSize = ScaledDynamicSize2Integer.create(dynamicSize)
                        .setWidthScale(0);

                    final List<Size2Integer> sizes = List.create();
                    try (final Disposable subscription = scaledDynamicSize.onChanged(() ->
                    {
                        sizes.add(Size2.create(scaledDynamicSize.getWidth(), scaledDynamicSize.getHeight()));
                    }))
                    {
                        dynamicSize.setWidth(200);

                        test.assertEqual(Iterable.create(), sizes);
                    }
                });

                runner.test("when innerSize and scaled size change", (Test test) ->
                {
                    final MutableDynamicSize2Integer dynamicSize = DynamicSize2Integer.create()
                        .setWidth(100);
                    final ScaledDynamicSize2Integer scaledDynamicSize = ScaledDynamicSize2Integer.create(dynamicSize)
                        .setWidthScale(0.1);

                    final List<Size2Integer> sizes = List.create();
                    try (final Disposable subscription = scaledDynamicSize.onChanged(() ->
                    {
                        sizes.add(Size2.create(scaledDynamicSize.getWidth(), scaledDynamicSize.getHeight()));
                    }))
                    {
                        dynamicSize.setWidth(200);

                        test.assertEqual(
                            Iterable.create(
                                Size2.create(20, 0)),
                            sizes);
                    }
                });

                runner.test("when inner size doesn't change but scaled size changes", (Test test) ->
                {
                    final MutableDynamicSize2Integer dynamicSize = DynamicSize2Integer.create()
                        .setWidth(100)
                        .setHeight(300);
                    final ScaledDynamicSize2Integer scaledDynamicSize = ScaledDynamicSize2Integer.create(dynamicSize)
                        .setScale(0.1, 0.2);

                    final List<Size2Integer> sizes = List.create();
                    try (final Disposable subscription = scaledDynamicSize.onChanged(() ->
                    {
                        sizes.add(Size2.create(scaledDynamicSize.getWidth(), scaledDynamicSize.getHeight()));
                    }))
                    {
                        scaledDynamicSize.setScale(1.1, 3);

                        test.assertEqual(
                            Iterable.create(
                                Size2.create(110, 900)),
                            sizes);
                    }
                });
            });
        });
    }
}
