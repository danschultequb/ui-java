package qub;

public interface DrawLineOptionsTests
{
    public static void test(TestRunner runner)
    {
        runner.testGroup(DrawLineOptions.class, () ->
        {
            runner.test("create()", (Test test) ->
            {
                final DrawLineOptions options = DrawLineOptions.create();
                test.assertNotNull(options);
                test.assertEqual(0, options.getStartX());
                test.assertEqual(0, options.getStartY());
                test.assertEqual(Point2Integer.zero, options.getStart());
                test.assertEqual(0, options.getEndX());
                test.assertEqual(0, options.getEndY());
                test.assertEqual(Point2Integer.zero, options.getEnd());
            });

            runner.testGroup("setStartX(int)", () ->
            {
                final Action1<Integer> setStartXTest = (Integer startX) ->
                {
                    runner.test("with " + startX, (Test test) ->
                    {
                        final DrawLineOptions options = DrawLineOptions.create();
                        final DrawLineOptions setStartXResult = options.setStartX(startX);
                        test.assertSame(options, setStartXResult);
                        test.assertEqual(startX, options.getStartX());
                    });
                };

                setStartXTest.run(-1);
                setStartXTest.run(0);
                setStartXTest.run(1);
            });

            runner.testGroup("setStartY(int)", () ->
            {
                final Action1<Integer> setStartYTest = (Integer startY) ->
                {
                    runner.test("with " + startY, (Test test) ->
                    {
                        final DrawLineOptions options = DrawLineOptions.create();
                        final DrawLineOptions setStartYResult = options.setStartY(startY);
                        test.assertSame(options, setStartYResult);
                        test.assertEqual(startY, options.getStartY());
                    });
                };

                setStartYTest.run(-1);
                setStartYTest.run(0);
                setStartYTest.run(1);
            });

            runner.testGroup("setStart(int,int)", () ->
            {
                final Action2<Integer,Integer> setStartTest = (Integer startX, Integer startY) ->
                {
                    runner.test("with " + English.andList(startX, startY), (Test test) ->
                    {
                        final DrawLineOptions options = DrawLineOptions.create();
                        final DrawLineOptions setStartResult = options.setStart(startX, startY);
                        test.assertSame(options, setStartResult);
                        test.assertEqual(Point2Integer.create(startX, startY), options.getStart());
                        test.assertEqual(startX, options.getStartX());
                        test.assertEqual(startY, options.getStartY());
                    });
                };

                setStartTest.run(1, 2);
            });

            runner.testGroup("setStart(Point2Integer)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final DrawLineOptions options = DrawLineOptions.create();
                    test.assertThrows(() -> options.setStart(null),
                        new PreConditionFailure("start cannot be null."));
                    test.assertEqual(Point2Integer.zero, options.getStart());
                    test.assertEqual(0, options.getStartX());
                    test.assertEqual(0, options.getStartY());
                });

                final Action1<Point2Integer> setStartTest = (Point2Integer start) ->
                {
                    runner.test("with " + start, (Test test) ->
                    {
                        final DrawLineOptions options = DrawLineOptions.create();
                        final DrawLineOptions setStartResult = options.setStart(start);
                        test.assertSame(options, setStartResult);
                        test.assertEqual(start, options.getStart());
                        test.assertEqual(start.getXAsInt(), options.getStartX());
                        test.assertEqual(start.getYAsInt(), options.getStartY());
                    });
                };

                setStartTest.run(Point2Integer.create(1, 2));
            });

            runner.testGroup("setEndX(int)", () ->
            {
                final Action1<Integer> setEndXTest = (Integer endX) ->
                {
                    runner.test("with " + endX, (Test test) ->
                    {
                        final DrawLineOptions options = DrawLineOptions.create();
                        final DrawLineOptions setEndXResult = options.setEndX(endX);
                        test.assertSame(options, setEndXResult);
                        test.assertEqual(endX, options.getEndX());
                    });
                };

                setEndXTest.run(-1);
                setEndXTest.run(0);
                setEndXTest.run(1);
            });

            runner.testGroup("setEndY(int)", () ->
            {
                final Action1<Integer> setEndYTest = (Integer endY) ->
                {
                    runner.test("with " + endY, (Test test) ->
                    {
                        final DrawLineOptions options = DrawLineOptions.create();
                        final DrawLineOptions setEndYResult = options.setEndY(endY);
                        test.assertSame(options, setEndYResult);
                        test.assertEqual(endY, options.getEndY());
                    });
                };

                setEndYTest.run(-1);
                setEndYTest.run(0);
                setEndYTest.run(1);
            });

            runner.testGroup("setEnd(int,int)", () ->
            {
                final Action2<Integer,Integer> setEndTest = (Integer endX, Integer endY) ->
                {
                    runner.test("with " + English.andList(endX, endY), (Test test) ->
                    {
                        final DrawLineOptions options = DrawLineOptions.create();
                        final DrawLineOptions setEndResult = options.setEnd(endX, endY);
                        test.assertSame(options, setEndResult);
                        test.assertEqual(Point2Integer.create(endX, endY), options.getEnd());
                        test.assertEqual(endX, options.getEndX());
                        test.assertEqual(endY, options.getEndY());
                    });
                };

                setEndTest.run(1, 2);
            });

            runner.testGroup("setEnd(Point2Integer)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final DrawLineOptions options = DrawLineOptions.create();
                    test.assertThrows(() -> options.setEnd(null),
                        new PreConditionFailure("end cannot be null."));
                    test.assertEqual(Point2Integer.zero, options.getEnd());
                    test.assertEqual(0, options.getEndX());
                    test.assertEqual(0, options.getEndY());
                });

                final Action1<Point2Integer> setEndTest = (Point2Integer end) ->
                {
                    runner.test("with " + end, (Test test) ->
                    {
                        final DrawLineOptions options = DrawLineOptions.create();
                        final DrawLineOptions setEndResult = options.setEnd(end);
                        test.assertSame(options, setEndResult);
                        test.assertEqual(end, options.getEnd());
                        test.assertEqual(end.getXAsInt(), options.getEndX());
                        test.assertEqual(end.getYAsInt(), options.getEndY());
                    });
                };

                setEndTest.run(Point2Integer.create(1, 2));
            });

            runner.testGroup("setX(int)", () ->
            {
                final Action1<Integer> setXTest = (Integer x) ->
                {
                    runner.test("with " + x, (Test test) ->
                    {
                        final DrawLineOptions options = DrawLineOptions.create();
                        final DrawLineOptions setXResult = options.setX(x);
                        test.assertSame(options, setXResult);
                        test.assertEqual(x, options.getStartX());
                        test.assertEqual(x, options.getEndX());
                    });
                };

                setXTest.run(-1);
                setXTest.run(0);
                setXTest.run(1);
            });

            runner.testGroup("setY(int)", () ->
            {
                final Action1<Integer> setYTest = (Integer y) ->
                {
                    runner.test("with " + y, (Test test) ->
                    {
                        final DrawLineOptions options = DrawLineOptions.create();
                        final DrawLineOptions setYResult = options.setY(y);
                        test.assertSame(options, setYResult);
                        test.assertEqual(y, options.getStartY());
                        test.assertEqual(y, options.getEndY());
                    });
                };

                setYTest.run(-1);
                setYTest.run(0);
                setYTest.run(1);
            });

            runner.testGroup("toJson()", () ->
            {
                final Action2<DrawLineOptions,JSONObject> toJsonTest = (DrawLineOptions options, JSONObject expected) ->
                {
                    runner.test("with " + options.toString(), (Test test) ->
                    {
                        test.assertEqual(expected, options.toJson());
                    });
                };

                toJsonTest.run(
                    DrawLineOptions.create(),
                    JSONObject.create()
                        .setNumber("startX", 0)
                        .setNumber("startY", 0)
                        .setNumber("endX", 0)
                        .setNumber("endY", 0));
                toJsonTest.run(
                    DrawLineOptions.create()
                        .setStartX(1),
                    JSONObject.create()
                        .setNumber("startX", 1)
                        .setNumber("startY", 0)
                        .setNumber("endX", 0)
                        .setNumber("endY", 0));
                toJsonTest.run(
                    DrawLineOptions.create()
                        .setStartY(2),
                    JSONObject.create()
                        .setNumber("startX", 0)
                        .setNumber("startY", 2)
                        .setNumber("endX", 0)
                        .setNumber("endY", 0));
                toJsonTest.run(
                    DrawLineOptions.create()
                        .setEndX(3),
                    JSONObject.create()
                        .setNumber("startX", 0)
                        .setNumber("startY", 0)
                        .setNumber("endX", 3)
                        .setNumber("endY", 0));
                toJsonTest.run(
                    DrawLineOptions.create()
                        .setEndY(4),
                    JSONObject.create()
                        .setNumber("startX", 0)
                        .setNumber("startY", 0)
                        .setNumber("endX", 0)
                        .setNumber("endY", 4));
            });

            runner.testGroup("toString()", () ->
            {
                final Action2<DrawLineOptions,String> toStringTest = (DrawLineOptions options, String expected) ->
                {
                    runner.test("with " + options.toString(), (Test test) ->
                    {
                        test.assertEqual(expected, options.toString());
                    });
                };

                toStringTest.run(
                    DrawLineOptions.create(),
                    "{\"startX\":0,\"startY\":0,\"endX\":0,\"endY\":0}");
                toStringTest.run(
                    DrawLineOptions.create()
                        .setStartX(1),
                    "{\"startX\":1,\"startY\":0,\"endX\":0,\"endY\":0}");
                toStringTest.run(
                    DrawLineOptions.create()
                        .setStartY(2),
                    "{\"startX\":0,\"startY\":2,\"endX\":0,\"endY\":0}");
                toStringTest.run(
                    DrawLineOptions.create()
                        .setEndX(3),
                    "{\"startX\":0,\"startY\":0,\"endX\":3,\"endY\":0}");
                toStringTest.run(
                    DrawLineOptions.create()
                        .setEndY(4),
                    "{\"startX\":0,\"startY\":0,\"endX\":0,\"endY\":4}");
            });

            runner.testGroup("equals(Object)", () ->
            {
                final Action3<DrawLineOptions,Object,Boolean> equalsTest = (DrawLineOptions lhs, Object rhs, Boolean expected) ->
                {
                    runner.test("with " + English.andList(lhs, rhs), (Test test) ->
                    {
                        test.assertEqual(expected, lhs.equals(rhs));
                    });
                };

                equalsTest.run(
                    DrawLineOptions.create(),
                    null,
                    false);
                equalsTest.run(
                    DrawLineOptions.create(),
                    "hello",
                    false);
                equalsTest.run(
                    DrawLineOptions.create(),
                    DrawLineOptions.create(),
                    true);
                equalsTest.run(
                    DrawLineOptions.create()
                        .setStartX(1),
                    DrawLineOptions.create()
                        .setStartX(2),
                    false);
                equalsTest.run(
                    DrawLineOptions.create()
                        .setStartX(1),
                    DrawLineOptions.create()
                        .setStartX(1),
                    true);
                equalsTest.run(
                    DrawLineOptions.create()
                        .setStartY(1),
                    DrawLineOptions.create()
                        .setStartY(2),
                    false);
                equalsTest.run(
                    DrawLineOptions.create()
                        .setStartY(1),
                    DrawLineOptions.create()
                        .setStartY(1),
                    true);
                equalsTest.run(
                    DrawLineOptions.create()
                        .setEndX(1),
                    DrawLineOptions.create()
                        .setEndX(2),
                    false);
                equalsTest.run(
                    DrawLineOptions.create()
                        .setEndX(1),
                    DrawLineOptions.create()
                        .setEndX(1),
                    true);
                equalsTest.run(
                    DrawLineOptions.create()
                        .setEndY(1),
                    DrawLineOptions.create()
                        .setEndY(2),
                    false);
                equalsTest.run(
                    DrawLineOptions.create()
                        .setEndY(1),
                    DrawLineOptions.create()
                        .setEndY(1),
                    true);
            });

            runner.testGroup("equals(DrawLineOptions)", () ->
            {
                final Action3<DrawLineOptions,DrawLineOptions,Boolean> equalsTest = (DrawLineOptions lhs, DrawLineOptions rhs, Boolean expected) ->
                {
                    runner.test("with " + English.andList(lhs, rhs), (Test test) ->
                    {
                        test.assertEqual(expected, lhs.equals(rhs));
                    });
                };

                equalsTest.run(
                    DrawLineOptions.create(),
                    null,
                    false);
                equalsTest.run(
                    DrawLineOptions.create(),
                    DrawLineOptions.create(),
                    true);
                equalsTest.run(
                    DrawLineOptions.create()
                        .setStartX(1),
                    DrawLineOptions.create()
                        .setStartX(2),
                    false);
                equalsTest.run(
                    DrawLineOptions.create()
                        .setStartX(1),
                    DrawLineOptions.create()
                        .setStartX(1),
                    true);
                equalsTest.run(
                    DrawLineOptions.create()
                        .setStartY(1),
                    DrawLineOptions.create()
                        .setStartY(2),
                    false);
                equalsTest.run(
                    DrawLineOptions.create()
                        .setStartY(1),
                    DrawLineOptions.create()
                        .setStartY(1),
                    true);
                equalsTest.run(
                    DrawLineOptions.create()
                        .setEndX(1),
                    DrawLineOptions.create()
                        .setEndX(2),
                    false);
                equalsTest.run(
                    DrawLineOptions.create()
                        .setEndX(1),
                    DrawLineOptions.create()
                        .setEndX(1),
                    true);
                equalsTest.run(
                    DrawLineOptions.create()
                        .setEndY(1),
                    DrawLineOptions.create()
                        .setEndY(2),
                    false);
                equalsTest.run(
                    DrawLineOptions.create()
                        .setEndY(1),
                    DrawLineOptions.create()
                        .setEndY(1),
                    true);
            });
        });
    }
}
