package qub;

public interface MutableTransform2Tests
{
    public static void test(TestRunner runner)
    {
        runner.testGroup(MutableTransform2.class, () ->
        {
            runner.test("create()", (Test test) ->
            {
                final MutableTransform2 transform = MutableTransform2.create();
                test.assertNotNull(transform);
            });
        });
    }

    public static void test(TestRunner runner, Function0<? extends MutableTransform2> creator)
    {
        runner.testGroup(MutableTransform2.class, () ->
        {
            Transform2Tests.test(runner, creator);

            runner.testGroup("translateX(int)", () ->
            {
                final Action1<Integer> translateXTest = (Integer x) ->
                {
                    runner.test("with " + x, (Test test) ->
                    {
                        final MutableTransform2 transform = creator.run();
                        final MutableTransform2 translateXResult = transform.translateX(x);
                        test.assertSame(transform, translateXResult);

                        test.assertEqual(Point2.create(x, 0), transform.apply(Point2.create(0, 0)));
                    });
                };

                translateXTest.run(-10);
                translateXTest.run(-1);
                translateXTest.run(0);
                translateXTest.run(1);
                translateXTest.run(10);
            });

            runner.testGroup("translateY(int)", () ->
            {
                final Action1<Integer> translateYTest = (Integer y) ->
                {
                    runner.test("with " + y, (Test test) ->
                    {
                        final MutableTransform2 transform = creator.run();
                        final MutableTransform2 translateYResult = transform.translateY(y);
                        test.assertSame(transform, translateYResult);

                        test.assertEqual(Point2.create(0, y), transform.apply(Point2.create(0, 0)));
                    });
                };

                translateYTest.run(-10);
                translateYTest.run(-1);
                translateYTest.run(0);
                translateYTest.run(1);
                translateYTest.run(10);
            });

            runner.testGroup("translate(int,int)", () ->
            {
                final Action2<Integer,Integer> translateTest = (Integer x, Integer y) ->
                {
                    runner.test("with " + English.andList(x, y), (Test test) ->
                    {
                        final MutableTransform2 transform = creator.run();
                        final MutableTransform2 translateYResult = transform.translate(x, y);
                        test.assertSame(transform, translateYResult);

                        test.assertEqual(Point2.create(x, y), transform.apply(Point2.create(0, 0)));
                    });
                };

                translateTest.run(-10, -10);
                translateTest.run(-10, -1);
                translateTest.run(-10, 0);
                translateTest.run(-10, 1);
                translateTest.run(-10, 10);
                translateTest.run(-1, -10);
                translateTest.run(-1, -1);
                translateTest.run(-1, 0);
                translateTest.run(-1, 1);
                translateTest.run(-1, 10);
            });
        });
    }
}
