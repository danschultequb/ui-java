package qub;

public interface DrawRectangleOptionsTests
{
    public static void test(TestRunner runner)
    {
        runner.testGroup(DrawRectangleOptions.class, () ->
        {
            runner.test("create()", (Test test) ->
            {
                final DrawRectangleOptions options = DrawRectangleOptions.create();
                test.assertNotNull(options);
                test.assertEqual(0, options.getLeftX());
                test.assertEqual(0, options.getWidth());
                test.assertEqual(0, options.getTopY());
                test.assertEqual(0, options.getHeight());
                test.assertNull(options.getLineColor());
                test.assertNull(options.getFillColor());
            });

            runner.testGroup("setLeftX(int)", () ->
            {
                final Action1<Integer> setLeftXTest = (Integer leftX) ->
                {
                    runner.test("with " + leftX, (Test test) ->
                    {
                        final DrawRectangleOptions options = DrawRectangleOptions.create();
                        final DrawRectangleOptions setLeftXResult = options.setLeftX(leftX);
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
                        final DrawRectangleOptions options = DrawRectangleOptions.create();
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
                        final DrawRectangleOptions options = DrawRectangleOptions.create();
                        final DrawRectangleOptions setWidthResult = options.setWidth(width);
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
                        final DrawRectangleOptions options = DrawRectangleOptions.create();
                        final DrawRectangleOptions setTopYResult = options.setTopY(topY);
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
                        final DrawRectangleOptions options = DrawRectangleOptions.create();
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
                        final DrawRectangleOptions options = DrawRectangleOptions.create();
                        final DrawRectangleOptions setHeightResult = options.setHeight(height);
                        test.assertSame(options, setHeightResult);
                        test.assertEqual(height, options.getHeight());
                    });
                };

                setHeightTest.run(0);
                setHeightTest.run(1);
            });

            runner.testGroup("setLineColor(Color)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final DrawRectangleOptions options = DrawRectangleOptions.create();
                    test.assertThrows(() -> options.setLineColor(null),
                        new PreConditionFailure("lineColor cannot be null."));
                    test.assertNull(options.getLineColor());
                });

                final Action1<Color> setLineColorTest = (Color lineColor) ->
                {
                    runner.test("with " + lineColor, (Test test) ->
                    {
                        final DrawRectangleOptions options = DrawRectangleOptions.create();
                        final DrawRectangleOptions setLineColorResult = options.setLineColor(lineColor);
                        test.assertSame(options, setLineColorResult);
                        test.assertEqual(lineColor, options.getLineColor());
                    });
                };

                setLineColorTest.run(Color.red);
                setLineColorTest.run(Color.green);
                setLineColorTest.run(Color.transparent);
                setLineColorTest.run(Color.create(1, 2, 3, 4));
            });

            runner.testGroup("setFillColor(Color)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final DrawRectangleOptions options = DrawRectangleOptions.create();
                    test.assertThrows(() -> options.setFillColor(null),
                        new PreConditionFailure("fillColor cannot be null."));
                    test.assertNull(options.getFillColor());
                });

                final Action1<Color> setFillColorTest = (Color fillColor) ->
                {
                    runner.test("with " + fillColor, (Test test) ->
                    {
                        final DrawRectangleOptions options = DrawRectangleOptions.create();
                        final DrawRectangleOptions setFillColorResult = options.setFillColor(fillColor);
                        test.assertSame(options, setFillColorResult);
                        test.assertEqual(fillColor, options.getFillColor());
                    });
                };

                setFillColorTest.run(Color.red);
                setFillColorTest.run(Color.green);
                setFillColorTest.run(Color.transparent);
                setFillColorTest.run(Color.create(1, 2, 3, 4));
            });

            runner.testGroup("toJson()", () ->
            {
                final Action2<DrawRectangleOptions,JSONObject> toJsonTest = (DrawRectangleOptions options, JSONObject expected) ->
                {
                    runner.test("with " + options, (Test test) ->
                    {
                        test.assertEqual(expected, options.toJson());
                    });
                };

                toJsonTest.run(
                    DrawRectangleOptions.create(),
                    JSONObject.create()
                        .setNumber("leftX", 0)
                        .setNumber("topY", 0)
                        .setNumber("width", 0)
                        .setNumber("height", 0));
                toJsonTest.run(
                    DrawRectangleOptions.create()
                        .setLeftX(1),
                    JSONObject.create()
                        .setNumber("leftX", 1)
                        .setNumber("topY", 0)
                        .setNumber("width", 0)
                        .setNumber("height", 0));
                toJsonTest.run(
                    DrawRectangleOptions.create()
                        .setWidth(2),
                    JSONObject.create()
                        .setNumber("leftX", 0)
                        .setNumber("topY", 0)
                        .setNumber("width", 2)
                        .setNumber("height", 0));
                toJsonTest.run(
                    DrawRectangleOptions.create()
                        .setTopY(3),
                    JSONObject.create()
                        .setNumber("leftX", 0)
                        .setNumber("topY", 3)
                        .setNumber("width", 0)
                        .setNumber("height", 0));
                toJsonTest.run(
                    DrawRectangleOptions.create()
                        .setHeight(4),
                    JSONObject.create()
                        .setNumber("leftX", 0)
                        .setNumber("topY", 0)
                        .setNumber("width", 0)
                        .setNumber("height", 4));
                toJsonTest.run(
                    DrawRectangleOptions.create()
                        .setLineColor(Color.red),
                    JSONObject.create()
                        .setNumber("leftX", 0)
                        .setNumber("topY", 0)
                        .setNumber("width", 0)
                        .setNumber("height", 0)
                        .setObject("lineColor", JSONObject.create()
                            .setNumber("red", 255)
                            .setNumber("green", 0)
                            .setNumber("blue", 0)
                            .setNumber("alpha", 255)));
                toJsonTest.run(
                    DrawRectangleOptions.create()
                        .setFillColor(Color.blue),
                    JSONObject.create()
                        .setNumber("leftX", 0)
                        .setNumber("topY", 0)
                        .setNumber("width", 0)
                        .setNumber("height", 0)
                        .setObject("fillColor", JSONObject.create()
                            .setNumber("red", 0)
                            .setNumber("green", 0)
                            .setNumber("blue", 255)
                            .setNumber("alpha", 255)));
            });

            runner.testGroup("toString()", () ->
            {
                final Action2<DrawRectangleOptions,String> toStringTest = (DrawRectangleOptions options, String expected) ->
                {
                    runner.test("with " + options.toString(), (Test test) ->
                    {
                        test.assertEqual(expected, options.toString());
                    });
                };

                toStringTest.run(
                    DrawRectangleOptions.create(),
                    "{\"leftX\":0,\"topY\":0,\"width\":0,\"height\":0}");
                toStringTest.run(
                    DrawRectangleOptions.create()
                        .setLeftX(1),
                    "{\"leftX\":1,\"topY\":0,\"width\":0,\"height\":0}");
                toStringTest.run(
                    DrawRectangleOptions.create()
                        .setWidth(2),
                    "{\"leftX\":0,\"topY\":0,\"width\":2,\"height\":0}");
                toStringTest.run(
                    DrawRectangleOptions.create()
                        .setTopY(3),
                    "{\"leftX\":0,\"topY\":3,\"width\":0,\"height\":0}");
                toStringTest.run(
                    DrawRectangleOptions.create()
                        .setHeight(4),
                    "{\"leftX\":0,\"topY\":0,\"width\":0,\"height\":4}");
                toStringTest.run(
                    DrawRectangleOptions.create()
                        .setLineColor(Color.red),
                    "{\"leftX\":0,\"topY\":0,\"width\":0,\"height\":0,\"lineColor\":{\"red\":255,\"green\":0,\"blue\":0,\"alpha\":255}}");
                toStringTest.run(
                    DrawRectangleOptions.create()
                        .setFillColor(Color.blue),
                    "{\"leftX\":0,\"topY\":0,\"width\":0,\"height\":0,\"fillColor\":{\"red\":0,\"green\":0,\"blue\":255,\"alpha\":255}}");
            });

            runner.testGroup("equals(Object)", () ->
            {
                final Action3<DrawRectangleOptions,Object,Boolean> equalsTest = (DrawRectangleOptions lhs, Object rhs, Boolean expected) ->
                {
                    runner.test("with " + English.andList(lhs, rhs), (Test test) ->
                    {
                        test.assertEqual(expected, lhs.equals(rhs));
                    });
                };

                equalsTest.run(
                    DrawRectangleOptions.create(),
                    null,
                    false);
                equalsTest.run(
                    DrawRectangleOptions.create(),
                    5,
                    false);
                equalsTest.run(
                    DrawRectangleOptions.create(),
                    DrawRectangleOptions.create(),
                    true);
                equalsTest.run(
                    DrawRectangleOptions.create()
                        .setLeftX(1),
                    DrawRectangleOptions.create()
                        .setLeftX(2),
                    false);
                equalsTest.run(
                    DrawRectangleOptions.create()
                        .setLeftX(1),
                    DrawRectangleOptions.create()
                        .setLeftX(1),
                    true);
                equalsTest.run(
                    DrawRectangleOptions.create()
                        .setWidth(1),
                    DrawRectangleOptions.create()
                        .setWidth(2),
                    false);
                equalsTest.run(
                    DrawRectangleOptions.create()
                        .setWidth(1),
                    DrawRectangleOptions.create()
                        .setWidth(1),
                    true);
                equalsTest.run(
                    DrawRectangleOptions.create()
                        .setTopY(1),
                    DrawRectangleOptions.create()
                        .setTopY(2),
                    false);
                equalsTest.run(
                    DrawRectangleOptions.create()
                        .setTopY(1),
                    DrawRectangleOptions.create()
                        .setTopY(1),
                    true);
                equalsTest.run(
                    DrawRectangleOptions.create()
                        .setHeight(1),
                    DrawRectangleOptions.create()
                        .setHeight(2),
                    false);
                equalsTest.run(
                    DrawRectangleOptions.create()
                        .setHeight(1),
                    DrawRectangleOptions.create()
                        .setHeight(1),
                    true);
                equalsTest.run(
                    DrawRectangleOptions.create()
                        .setLineColor(Color.green),
                    DrawRectangleOptions.create()
                        .setLineColor(Color.white),
                    false);
                equalsTest.run(
                    DrawRectangleOptions.create()
                        .setLineColor(Color.green),
                    DrawRectangleOptions.create()
                        .setLineColor(Color.green),
                    true);
                equalsTest.run(
                    DrawRectangleOptions.create()
                        .setFillColor(Color.green),
                    DrawRectangleOptions.create()
                        .setFillColor(Color.white),
                    false);
                equalsTest.run(
                    DrawRectangleOptions.create()
                        .setFillColor(Color.green),
                    DrawRectangleOptions.create()
                        .setFillColor(Color.green),
                    true);
            });

            runner.testGroup("equals(DrawRectangleOptions)", () ->
            {
                final Action3<DrawRectangleOptions,DrawRectangleOptions,Boolean> equalsTest = (DrawRectangleOptions lhs, DrawRectangleOptions rhs, Boolean expected) ->
                {
                    runner.test("with " + English.andList(lhs, rhs), (Test test) ->
                    {
                        test.assertEqual(expected, lhs.equals(rhs));
                    });
                };

                equalsTest.run(
                    DrawRectangleOptions.create(),
                    null,
                    false);
                equalsTest.run(
                    DrawRectangleOptions.create(),
                    DrawRectangleOptions.create(),
                    true);
                equalsTest.run(
                    DrawRectangleOptions.create()
                        .setLeftX(1),
                    DrawRectangleOptions.create()
                        .setLeftX(2),
                    false);
                equalsTest.run(
                    DrawRectangleOptions.create()
                        .setLeftX(1),
                    DrawRectangleOptions.create()
                        .setLeftX(1),
                    true);
                equalsTest.run(
                    DrawRectangleOptions.create()
                        .setWidth(1),
                    DrawRectangleOptions.create()
                        .setWidth(2),
                    false);
                equalsTest.run(
                    DrawRectangleOptions.create()
                        .setWidth(1),
                    DrawRectangleOptions.create()
                        .setWidth(1),
                    true);
                equalsTest.run(
                    DrawRectangleOptions.create()
                        .setTopY(1),
                    DrawRectangleOptions.create()
                        .setTopY(2),
                    false);
                equalsTest.run(
                    DrawRectangleOptions.create()
                        .setTopY(1),
                    DrawRectangleOptions.create()
                        .setTopY(1),
                    true);
                equalsTest.run(
                    DrawRectangleOptions.create()
                        .setHeight(1),
                    DrawRectangleOptions.create()
                        .setHeight(2),
                    false);
                equalsTest.run(
                    DrawRectangleOptions.create()
                        .setHeight(1),
                    DrawRectangleOptions.create()
                        .setHeight(1),
                    true);
            });
        });
    }
}
