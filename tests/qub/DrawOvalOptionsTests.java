package qub;

public interface DrawOvalOptionsTests
{
    public static void test(TestRunner runner)
    {
        runner.testGroup(DrawOvalOptions.class, () ->
        {
            runner.test("create()", (Test test) ->
            {
                final DrawOvalOptions options = DrawOvalOptions.create();
                test.assertNotNull(options);
                test.assertEqual(0, options.getLeftX());
                test.assertEqual(0, options.getWidth());
                test.assertEqual(0, options.getTopY());
                test.assertEqual(0, options.getHeight());
            });

            runner.testGroup("setLeftX(int)", () ->
            {
                final Action1<Integer> setLeftXTest = (Integer leftX) ->
                {
                    runner.test("with " + leftX, (Test test) ->
                    {
                        final DrawOvalOptions options = DrawOvalOptions.create();
                        final DrawOvalOptions setLeftXResult = options.setLeftX(leftX);
                        test.assertSame(options, setLeftXResult);
                        test.assertEqual(leftX, options.getLeftX());
                    });
                };

                setLeftXTest.run(-1);
                setLeftXTest.run(0);
                setLeftXTest.run(1);
            });

            runner.testGroup("setWidth(int)", () ->
            {
                final Action2<Integer,Throwable> setWidthErrorTest = (Integer width, Throwable expected) ->
                {
                    runner.test("with " + width, (Test test) ->
                    {
                        final DrawOvalOptions options = DrawOvalOptions.create();
                        test.assertThrows(() -> options.setWidth(width),
                            expected);
                        test.assertEqual(0, options.getWidth());
                    });
                };

                setWidthErrorTest.run(-1, new PreConditionFailure("width (-1) must be greater than or equal to 0."));

                final Action1<Integer> setWidthTest = (Integer width) ->
                {
                    runner.test("with " + width, (Test test) ->
                    {
                        final DrawOvalOptions options = DrawOvalOptions.create();
                        final DrawOvalOptions setWidthResult = options.setWidth(width);
                        test.assertSame(options, setWidthResult);
                        test.assertEqual(width, options.getWidth());
                    });
                };

                setWidthTest.run(0);
                setWidthTest.run(1);
            });

            runner.testGroup("setTopY(int)", () ->
            {
                final Action1<Integer> setTopYTest = (Integer topY) ->
                {
                    runner.test("with " + topY, (Test test) ->
                    {
                        final DrawOvalOptions options = DrawOvalOptions.create();
                        final DrawOvalOptions setTopYResult = options.setTopY(topY);
                        test.assertSame(options, setTopYResult);
                        test.assertEqual(topY, options.getTopY());
                    });
                };

                setTopYTest.run(-1);
                setTopYTest.run(0);
                setTopYTest.run(1);
            });

            runner.testGroup("setHeight(int)", () ->
            {
                final Action2<Integer,Throwable> setHeightErrorTest = (Integer height, Throwable expected) ->
                {
                    runner.test("with " + height, (Test test) ->
                    {
                        final DrawOvalOptions options = DrawOvalOptions.create();
                        test.assertThrows(() -> options.setHeight(height),
                            expected);
                        test.assertEqual(0, options.getHeight());
                    });
                };

                setHeightErrorTest.run(-1, new PreConditionFailure("height (-1) must be greater than or equal to 0."));

                final Action1<Integer> setHeightTest = (Integer height) ->
                {
                    runner.test("with " + height, (Test test) ->
                    {
                        final DrawOvalOptions options = DrawOvalOptions.create();
                        final DrawOvalOptions setHeightResult = options.setHeight(height);
                        test.assertSame(options, setHeightResult);
                        test.assertEqual(height, options.getHeight());
                    });
                };

                setHeightTest.run(0);
                setHeightTest.run(1);
            });

            runner.testGroup("toJson()", () ->
            {
                final Action2<DrawOvalOptions,JSONObject> toJsonTest = (DrawOvalOptions options, JSONObject expected) ->
                {
                    runner.test("with " + options, (Test test) ->
                    {
                        test.assertEqual(expected, options.toJson());
                    });
                };

                toJsonTest.run(
                    DrawOvalOptions.create(),
                    JSONObject.create()
                        .setNumber("leftX", 0)
                        .setNumber("topY", 0)
                        .setNumber("width", 0)
                        .setNumber("height", 0));
                toJsonTest.run(
                    DrawOvalOptions.create()
                        .setLeftX(1),
                    JSONObject.create()
                        .setNumber("leftX", 1)
                        .setNumber("topY", 0)
                        .setNumber("width", 0)
                        .setNumber("height", 0));
                toJsonTest.run(
                    DrawOvalOptions.create()
                        .setWidth(2),
                    JSONObject.create()
                        .setNumber("leftX", 0)
                        .setNumber("topY", 0)
                        .setNumber("width", 2)
                        .setNumber("height", 0));
                toJsonTest.run(
                    DrawOvalOptions.create()
                        .setTopY(3),
                    JSONObject.create()
                        .setNumber("leftX", 0)
                        .setNumber("topY", 3)
                        .setNumber("width", 0)
                        .setNumber("height", 0));
                toJsonTest.run(
                    DrawOvalOptions.create()
                        .setHeight(4),
                    JSONObject.create()
                        .setNumber("leftX", 0)
                        .setNumber("topY", 0)
                        .setNumber("width", 0)
                        .setNumber("height", 4));
            });

            runner.testGroup("toString()", () ->
            {
                final Action2<DrawOvalOptions,String> toStringTest = (DrawOvalOptions options, String expected) ->
                {
                    runner.test("with " + options.toString(), (Test test) ->
                    {
                        test.assertEqual(expected, options.toString());
                    });
                };

                toStringTest.run(
                    DrawOvalOptions.create(),
                    "{\"leftX\":0,\"topY\":0,\"width\":0,\"height\":0}");
                toStringTest.run(
                    DrawOvalOptions.create()
                        .setLeftX(1),
                    "{\"leftX\":1,\"topY\":0,\"width\":0,\"height\":0}");
                toStringTest.run(
                    DrawOvalOptions.create()
                        .setWidth(2),
                    "{\"leftX\":0,\"topY\":0,\"width\":2,\"height\":0}");
                toStringTest.run(
                    DrawOvalOptions.create()
                        .setTopY(3),
                    "{\"leftX\":0,\"topY\":3,\"width\":0,\"height\":0}");
                toStringTest.run(
                    DrawOvalOptions.create()
                        .setHeight(4),
                    "{\"leftX\":0,\"topY\":0,\"width\":0,\"height\":4}");
            });

            runner.testGroup("equals(Object)", () ->
            {
                final Action3<DrawOvalOptions,Object,Boolean> equalsTest = (DrawOvalOptions lhs, Object rhs, Boolean expected) ->
                {
                    runner.test("with " + English.andList(lhs, rhs), (Test test) ->
                    {
                        test.assertEqual(expected, lhs.equals(rhs));
                    });
                };

                equalsTest.run(
                    DrawOvalOptions.create(),
                    null,
                    false);
                equalsTest.run(
                    DrawOvalOptions.create(),
                    5,
                    false);
                equalsTest.run(
                    DrawOvalOptions.create(),
                    DrawOvalOptions.create(),
                    true);
                equalsTest.run(
                    DrawOvalOptions.create()
                        .setLeftX(1),
                    DrawOvalOptions.create()
                        .setLeftX(2),
                    false);
                equalsTest.run(
                    DrawOvalOptions.create()
                        .setLeftX(1),
                    DrawOvalOptions.create()
                        .setLeftX(1),
                    true);
                equalsTest.run(
                    DrawOvalOptions.create()
                        .setWidth(1),
                    DrawOvalOptions.create()
                        .setWidth(2),
                    false);
                equalsTest.run(
                    DrawOvalOptions.create()
                        .setWidth(1),
                    DrawOvalOptions.create()
                        .setWidth(1),
                    true);
                equalsTest.run(
                    DrawOvalOptions.create()
                        .setTopY(1),
                    DrawOvalOptions.create()
                        .setTopY(2),
                    false);
                equalsTest.run(
                    DrawOvalOptions.create()
                        .setTopY(1),
                    DrawOvalOptions.create()
                        .setTopY(1),
                    true);
                equalsTest.run(
                    DrawOvalOptions.create()
                        .setHeight(1),
                    DrawOvalOptions.create()
                        .setHeight(2),
                    false);
                equalsTest.run(
                    DrawOvalOptions.create()
                        .setHeight(1),
                    DrawOvalOptions.create()
                        .setHeight(1),
                    true);
            });

            runner.testGroup("equals(DrawOvalOptions)", () ->
            {
                final Action3<DrawOvalOptions,DrawOvalOptions,Boolean> equalsTest = (DrawOvalOptions lhs, DrawOvalOptions rhs, Boolean expected) ->
                {
                    runner.test("with " + English.andList(lhs, rhs), (Test test) ->
                    {
                        test.assertEqual(expected, lhs.equals(rhs));
                    });
                };

                equalsTest.run(
                    DrawOvalOptions.create(),
                    null,
                    false);
                equalsTest.run(
                    DrawOvalOptions.create(),
                    DrawOvalOptions.create(),
                    true);
                equalsTest.run(
                    DrawOvalOptions.create()
                        .setLeftX(1),
                    DrawOvalOptions.create()
                        .setLeftX(2),
                    false);
                equalsTest.run(
                    DrawOvalOptions.create()
                        .setLeftX(1),
                    DrawOvalOptions.create()
                        .setLeftX(1),
                    true);
                equalsTest.run(
                    DrawOvalOptions.create()
                        .setWidth(1),
                    DrawOvalOptions.create()
                        .setWidth(2),
                    false);
                equalsTest.run(
                    DrawOvalOptions.create()
                        .setWidth(1),
                    DrawOvalOptions.create()
                        .setWidth(1),
                    true);
                equalsTest.run(
                    DrawOvalOptions.create()
                        .setTopY(1),
                    DrawOvalOptions.create()
                        .setTopY(2),
                    false);
                equalsTest.run(
                    DrawOvalOptions.create()
                        .setTopY(1),
                    DrawOvalOptions.create()
                        .setTopY(1),
                    true);
                equalsTest.run(
                    DrawOvalOptions.create()
                        .setHeight(1),
                    DrawOvalOptions.create()
                        .setHeight(2),
                    false);
                equalsTest.run(
                    DrawOvalOptions.create()
                        .setHeight(1),
                    DrawOvalOptions.create()
                        .setHeight(1),
                    true);
            });
        });
    }
}
