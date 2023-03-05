package qub;

public interface DynamicSize2IntegerTests
{
    public static void test(TestRunner runner)
    {
        runner.testGroup(DynamicSize2Integer.class, () ->
        {
            runner.test("create()", (Test test) ->
            {
                final MutableDynamicSize2Integer dynamicSize = DynamicSize2Integer.create();
                test.assertNotNull(dynamicSize);
            });

            runner.testGroup("create(int,int)", () ->
            {
                final Action3<Integer,Integer,Throwable> createErrorTest = (Integer width, Integer height, Throwable expected) ->
                {
                    runner.test("with " + English.andList(width, height), (Test test) ->
                    {
                        test.assertThrows(() -> DynamicSize2Integer.create(width, height),
                            expected);
                    });
                };

                createErrorTest.run(-1, 0, new PreConditionFailure("width (-1) must be greater than or equal to 0."));
                createErrorTest.run(0, -1, new PreConditionFailure("height (-1) must be greater than or equal to 0."));

                final Action2<Integer,Integer> createTest = (Integer width, Integer height) ->
                {
                    runner.test("with " + English.andList(width, height), (Test test) ->
                    {
                        final MutableDynamicSize2Integer dynamicSize = DynamicSize2Integer.create(width, height);
                        test.assertNotNull(dynamicSize);
                        test.assertEqual(width, dynamicSize.getWidth());
                        test.assertEqual(height, dynamicSize.getHeight());
                    });
                };

                createTest.run(0, 0);
                createTest.run(1, 0);
                createTest.run(0, 1);
                createTest.run(1, 2);
            });

            runner.testGroup("create(Size2<Integer>)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    test.assertThrows(() -> DynamicSize2Integer.create(null),
                        new PreConditionFailure("size cannot be null."));
                });

                final Action1<Size2<Integer>> createTest = (Size2<Integer> size) ->
                {
                    runner.test("with " + size, (Test test) ->
                    {
                        final MutableDynamicSize2Integer dynamicSize = DynamicSize2Integer.create(size);
                        test.assertNotNull(dynamicSize);
                        test.assertEqual(size, dynamicSize);
                    });
                };

                createTest.run(Size2.create(0, 0));
                createTest.run(Size2.create(1, 0));
                createTest.run(Size2.create(0, 1));
                createTest.run(Size2.create(1, 2));
            });
        });
    }

    public static void test(TestRunner runner, Function0<? extends DynamicSize2Integer> creator)
    {
        runner.testGroup(DynamicSize2Integer.class, () ->
        {
            runner.testGroup("onChanged(Action0)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final DynamicSize2Integer dynamicSize = creator.run();
                    test.assertThrows(() -> dynamicSize.onChanged((Action0)null),
                        new PreConditionFailure("action cannot be null."));
                });
            });

            runner.testGroup("onChanged(Action1<SizeChange>)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final DynamicSize2Integer dynamicSize = creator.run();
                    test.assertThrows(() -> dynamicSize.onChanged((Action1<SizeChange>)null),
                        new PreConditionFailure("action cannot be null."));
                });
            });

            runner.testGroup("scaleWidth(double)", () ->
            {
                runner.test("with negative", (Test test) ->
                {
                    final DynamicSize2Integer dynamicSize = creator.run();
                    test.assertThrows(() -> dynamicSize.scaleWidth(-1),
                        new PreConditionFailure("widthScale (-1.0) must be greater than or equal to 0.0."));
                });

                final Action1<Double> scaleWidthTest = (Double scale) ->
                {
                    runner.test("with " + scale, (Test test) ->
                    {
                        final DynamicSize2Integer dynamicSize = creator.run();
                        final ScaledDynamicSize2Integer scaledDynamicSize = dynamicSize.scaleWidth(scale);
                        test.assertNotNull(scaledDynamicSize);
                        test.assertEqual((int)(dynamicSize.getWidthAsInt() * scale), scaledDynamicSize.getWidthAsInt());
                        test.assertEqual(dynamicSize.getHeightAsInt(), scaledDynamicSize.getHeightAsInt());
                    });
                };

                scaleWidthTest.run(0.0);
                scaleWidthTest.run(0.5);
                scaleWidthTest.run(1.0);
                scaleWidthTest.run(2.0);
            });

            runner.testGroup("scaleHeight(double)", () ->
            {
                runner.test("with negative", (Test test) ->
                {
                    final DynamicSize2Integer dynamicSize = creator.run();
                    test.assertThrows(() -> dynamicSize.scaleHeight(-1),
                        new PreConditionFailure("heightScale (-1.0) must be greater than or equal to 0.0."));
                });

                final Action1<Double> scaleHeightTest = (Double scale) ->
                {
                    runner.test("with " + scale, (Test test) ->
                    {
                        final DynamicSize2Integer dynamicSize = creator.run();
                        final ScaledDynamicSize2Integer scaledDynamicSize = dynamicSize.scaleHeight(scale);
                        test.assertNotNull(scaledDynamicSize);
                        test.assertEqual(dynamicSize.getWidthAsInt(), scaledDynamicSize.getWidthAsInt());
                        test.assertEqual((int)(dynamicSize.getHeightAsInt() * scale), scaledDynamicSize.getHeightAsInt());
                    });
                };

                scaleHeightTest.run(0.0);
                scaleHeightTest.run(0.5);
                scaleHeightTest.run(1.0);
                scaleHeightTest.run(2.0);
            });

            runner.testGroup("scale(double)", () ->
            {
                runner.test("with negative", (Test test) ->
                {
                    final DynamicSize2Integer dynamicSize = creator.run();
                    test.assertThrows(() -> dynamicSize.scale(-1),
                        new PreConditionFailure("scale (-1.0) must be greater than or equal to 0.0."));
                });

                final Action1<Double> scaleTest = (Double scale) ->
                {
                    runner.test("with " + scale, (Test test) ->
                    {
                        final DynamicSize2Integer dynamicSize = creator.run();
                        final ScaledDynamicSize2Integer scaledDynamicSize = dynamicSize.scale(scale);
                        test.assertNotNull(scaledDynamicSize);
                        test.assertEqual((int)(dynamicSize.getWidthAsInt() * scale), scaledDynamicSize.getWidthAsInt());
                        test.assertEqual((int)(dynamicSize.getHeightAsInt() * scale), scaledDynamicSize.getHeightAsInt());
                    });
                };

                scaleTest.run(0.0);
                scaleTest.run(0.5);
                scaleTest.run(1.0);
                scaleTest.run(2.0);
            });

            runner.testGroup("scale(double,double)", () ->
            {
                final Action3<Double,Double,Throwable> scaleErrorTest = (Double widthScale, Double heightScale, Throwable expected) ->
                {
                    runner.test("with " + English.andList(widthScale, heightScale), (Test test) ->
                    {
                        final DynamicSize2Integer dynamicSize = creator.run();
                        test.assertThrows(() -> dynamicSize.scale(widthScale, heightScale),
                            expected);
                    });
                };

                scaleErrorTest.run(-1.0, 0.0, new PreConditionFailure("widthScale (-1.0) must be greater than or equal to 0.0."));
                scaleErrorTest.run(0.0, -1.0, new PreConditionFailure("heightScale (-1.0) must be greater than or equal to 0.0."));

                final Action2<Double,Double> scaleTest = (Double widthScale, Double heightScale) ->
                {
                    runner.test("with " + English.andList(widthScale, heightScale), (Test test) ->
                    {
                        final DynamicSize2Integer dynamicSize = creator.run();
                        final ScaledDynamicSize2Integer scaledDynamicSize = dynamicSize.scale(widthScale, heightScale);
                        test.assertNotNull(scaledDynamicSize);
                        test.assertEqual((int)(dynamicSize.getWidthAsInt() * widthScale), scaledDynamicSize.getWidthAsInt());
                        test.assertEqual((int)(dynamicSize.getHeightAsInt() * heightScale), scaledDynamicSize.getHeightAsInt());
                    });
                };

                scaleTest.run(0.0, 0.0);
                scaleTest.run(0.0, 0.5);
                scaleTest.run(0.0, 1.0);
                scaleTest.run(0.0, 2.0);
                scaleTest.run(0.5, 0.0);
                scaleTest.run(0.5, 0.5);
                scaleTest.run(0.5, 1.0);
                scaleTest.run(0.5, 2.0);
                scaleTest.run(1.0, 0.0);
                scaleTest.run(1.0, 0.5);
                scaleTest.run(1.0, 1.0);
                scaleTest.run(1.0, 2.0);
                scaleTest.run(2.0, 0.0);
                scaleTest.run(2.0, 0.5);
                scaleTest.run(2.0, 1.0);
                scaleTest.run(2.0, 2.0);
            });
        });
    }
}
