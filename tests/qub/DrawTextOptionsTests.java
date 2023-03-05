package qub;

public interface DrawTextOptionsTests
{
    public static void test(TestRunner runner)
    {
        runner.testGroup(DrawTextOptions.class, () ->
        {
            runner.test("create()", (Test test) ->
            {
                final DrawTextOptions options = DrawTextOptions.create();
                test.assertNotNull(options);
                test.assertNull(options.getTopY());
                test.assertNull(options.getCenterY());
                test.assertNull(options.getBaselineY());
                test.assertNull(options.getBottomY());
                test.assertNull(options.getLeftX());
                test.assertNull(options.getCenterX());
                test.assertNull(options.getRightX());
                test.assertNull(options.getText());
            });

            runner.testGroup("setLeftX(int)", () ->
            {
                final Action3<String,DrawTextOptions,Throwable> setLeftXErrorTest = (String testName, DrawTextOptions options, Throwable expected) ->
                {
                    runner.test(testName, (Test test) ->
                    {
                        final Integer leftX = options.getLeftX();
                        test.assertThrows(() -> options.setLeftX(5),
                            expected);
                        test.assertEqual(leftX, options.getLeftX());
                    });
                };

                setLeftXErrorTest.run("with existing leftX value set",
                    DrawTextOptions.create().setLeftX(1),
                    new PreConditionFailure("this.hasX() cannot be true."));
                setLeftXErrorTest.run("with existing centerX value set",
                    DrawTextOptions.create().setCenterX(1),
                    new PreConditionFailure("this.hasX() cannot be true."));
                setLeftXErrorTest.run("with existing rightX value set",
                    DrawTextOptions.create().setRightX(2),
                    new PreConditionFailure("this.hasX() cannot be true."));

                final Action3<String,DrawTextOptions,Integer> setLeftXTest = (String testName, DrawTextOptions options, Integer leftX) ->
                {
                    runner.test(testName, (Test test) ->
                    {
                        final DrawTextOptions setLeftXResult = options.setLeftX(leftX);
                        test.assertSame(options, setLeftXResult);
                        test.assertEqual(leftX, options.getLeftX());
                        test.assertNull(options.getCenterX());
                        test.assertNull(options.getRightX());
                    });
                };

                setLeftXTest.run("with no x-value set",
                    DrawTextOptions.create(),
                    20);
            });

            runner.testGroup("setCenterX(int)", () ->
            {
                final Action3<String,DrawTextOptions,Throwable> setCenterXErrorTest = (String testName, DrawTextOptions options, Throwable expected) ->
                {
                    runner.test(testName, (Test test) ->
                    {
                        final Integer centerX = options.getCenterX();
                        test.assertThrows(() -> options.setCenterX(5),
                            expected);
                        test.assertEqual(centerX, options.getCenterX());
                    });
                };

                setCenterXErrorTest.run("with existing leftX value set",
                    DrawTextOptions.create().setLeftX(1),
                    new PreConditionFailure("this.hasX() cannot be true."));
                setCenterXErrorTest.run("with existing centerX value set",
                    DrawTextOptions.create().setCenterX(1),
                    new PreConditionFailure("this.hasX() cannot be true."));
                setCenterXErrorTest.run("with existing rightX value set",
                    DrawTextOptions.create().setRightX(2),
                    new PreConditionFailure("this.hasX() cannot be true."));

                final Action3<String,DrawTextOptions,Integer> setCenterXTest = (String testName, DrawTextOptions options, Integer centerX) ->
                {
                    runner.test(testName, (Test test) ->
                    {
                        final DrawTextOptions setCenterXResult = options.setCenterX(centerX);
                        test.assertSame(options, setCenterXResult);
                        test.assertEqual(centerX, options.getCenterX());
                        test.assertNull(options.getLeftX());
                        test.assertNull(options.getRightX());
                    });
                };

                setCenterXTest.run("with no x-value set",
                    DrawTextOptions.create(),
                    20);
            });

            runner.testGroup("setRightX(int)", () ->
            {
                final Action3<String,DrawTextOptions,Throwable> setRightXErrorTest = (String testName, DrawTextOptions options, Throwable expected) ->
                {
                    runner.test(testName, (Test test) ->
                    {
                        final Integer rightX = options.getRightX();
                        test.assertThrows(() -> options.setRightX(5),
                            expected);
                        test.assertEqual(rightX, options.getRightX());
                    });
                };

                setRightXErrorTest.run("with existing leftX value set",
                    DrawTextOptions.create().setLeftX(1),
                    new PreConditionFailure("this.hasX() cannot be true."));
                setRightXErrorTest.run("with existing centerX value set",
                    DrawTextOptions.create().setCenterX(2),
                    new PreConditionFailure("this.hasX() cannot be true."));
                setRightXErrorTest.run("with existing rightX value set",
                    DrawTextOptions.create().setRightX(2),
                    new PreConditionFailure("this.hasX() cannot be true."));

                final Action3<String,DrawTextOptions,Integer> setRightXTest = (String testName, DrawTextOptions options, Integer rightX) ->
                {
                    runner.test(testName, (Test test) ->
                    {
                        final DrawTextOptions setRightXResult = options.setRightX(rightX);
                        test.assertSame(options, setRightXResult);
                        test.assertEqual(rightX, options.getRightX());
                        test.assertNull(options.getLeftX());
                        test.assertNull(options.getCenterX());
                    });
                };

                setRightXTest.run("with no x-value set",
                    DrawTextOptions.create(),
                    20);
            });

            runner.testGroup("setTopY(int)", () ->
            {
                final Action3<String,DrawTextOptions,Throwable> setTopYErrorTest = (String testName, DrawTextOptions options, Throwable expected) ->
                {
                    runner.test(testName, (Test test) ->
                    {
                        final Integer topY = options.getTopY();
                        test.assertThrows(() -> options.setTopY(5),
                            expected);
                        test.assertEqual(topY, options.getTopY());
                    });
                };

                setTopYErrorTest.run("with existing topY value set",
                    DrawTextOptions.create().setTopY(2),
                    new PreConditionFailure("this.hasY() cannot be true."));
                setTopYErrorTest.run("with existing centerY value set",
                    DrawTextOptions.create().setCenterY(2),
                    new PreConditionFailure("this.hasY() cannot be true."));
                setTopYErrorTest.run("with existing baselineY value set",
                    DrawTextOptions.create().setBaselineY(2),
                    new PreConditionFailure("this.hasY() cannot be true."));
                setTopYErrorTest.run("with existing bottomY value set",
                    DrawTextOptions.create().setBottomY(2),
                    new PreConditionFailure("this.hasY() cannot be true."));

                final Action3<String,DrawTextOptions,Integer> setTopYTest = (String testName, DrawTextOptions options, Integer topY) ->
                {
                    runner.test(testName, (Test test) ->
                    {
                        final DrawTextOptions setTopYResult = options.setTopY(topY);
                        test.assertSame(options, setTopYResult);
                        test.assertEqual(topY, options.getTopY());
                        test.assertNull(options.getCenterY());
                        test.assertNull(options.getBaselineY());
                        test.assertNull(options.getBottomY());
                    });
                };

                setTopYTest.run("with no y-value set",
                    DrawTextOptions.create(),
                    20);
            });

            runner.testGroup("setCenterY(int)", () ->
            {
                final Action3<String,DrawTextOptions,Throwable> setCenterYErrorTest = (String testName, DrawTextOptions options, Throwable expected) ->
                {
                    runner.test(testName, (Test test) ->
                    {
                        final Integer centerY = options.getCenterY();
                        test.assertThrows(() -> options.setCenterY(5),
                            expected);
                        test.assertEqual(centerY, options.getCenterY());
                    });
                };

                setCenterYErrorTest.run("with existing topY value set",
                    DrawTextOptions.create().setTopY(2),
                    new PreConditionFailure("this.hasY() cannot be true."));
                setCenterYErrorTest.run("with existing centerY value set",
                    DrawTextOptions.create().setCenterY(2),
                    new PreConditionFailure("this.hasY() cannot be true."));
                setCenterYErrorTest.run("with existing baselineY value set",
                    DrawTextOptions.create().setBaselineY(2),
                    new PreConditionFailure("this.hasY() cannot be true."));
                setCenterYErrorTest.run("with existing bottomY value set",
                    DrawTextOptions.create().setBottomY(2),
                    new PreConditionFailure("this.hasY() cannot be true."));

                final Action3<String,DrawTextOptions,Integer> setCenterYTest = (String testName, DrawTextOptions options, Integer centerY) ->
                {
                    runner.test(testName, (Test test) ->
                    {
                        final DrawTextOptions setCenterYResult = options.setCenterY(centerY);
                        test.assertSame(options, setCenterYResult);
                        test.assertEqual(centerY, options.getCenterY());
                        test.assertNull(options.getTopY());
                        test.assertNull(options.getBaselineY());
                        test.assertNull(options.getBottomY());
                    });
                };

                setCenterYTest.run("with no y-value set",
                    DrawTextOptions.create(),
                    20);
            });

            runner.testGroup("setBaselineY(int)", () ->
            {
                final Action3<String,DrawTextOptions,Throwable> setBaselineYErrorTest = (String testName, DrawTextOptions options, Throwable expected) ->
                {
                    runner.test(testName, (Test test) ->
                    {
                        final Integer baselineY = options.getBaselineY();
                        test.assertThrows(() -> options.setBaselineY(5),
                            expected);
                        test.assertEqual(baselineY, options.getBaselineY());
                    });
                };

                setBaselineYErrorTest.run("with existing topY value set",
                    DrawTextOptions.create().setTopY(2),
                    new PreConditionFailure("this.hasY() cannot be true."));
                setBaselineYErrorTest.run("with existing centerY value set",
                    DrawTextOptions.create().setCenterY(2),
                    new PreConditionFailure("this.hasY() cannot be true."));
                setBaselineYErrorTest.run("with existing baselineY value set",
                    DrawTextOptions.create().setBaselineY(2),
                    new PreConditionFailure("this.hasY() cannot be true."));
                setBaselineYErrorTest.run("with existing bottomY value set",
                    DrawTextOptions.create().setBottomY(2),
                    new PreConditionFailure("this.hasY() cannot be true."));

                final Action3<String,DrawTextOptions,Integer> setBaselineYTest = (String testName, DrawTextOptions options, Integer baselineY) ->
                {
                    runner.test(testName, (Test test) ->
                    {
                        final DrawTextOptions setBaselineYResult = options.setBaselineY(baselineY);
                        test.assertSame(options, setBaselineYResult);
                        test.assertEqual(baselineY, options.getBaselineY());
                        test.assertNull(options.getTopY());
                        test.assertNull(options.getCenterY());
                        test.assertNull(options.getBottomY());
                    });
                };

                setBaselineYTest.run("with no y-value set",
                    DrawTextOptions.create(),
                    20);
            });

            runner.testGroup("setBottomY(int)", () ->
            {
                final Action3<String,DrawTextOptions,Throwable> setBottomYErrorTest = (String testName, DrawTextOptions options, Throwable expected) ->
                {
                    runner.test(testName, (Test test) ->
                    {
                        final Integer bottomY = options.getBottomY();
                        test.assertThrows(() -> options.setBottomY(5),
                            expected);
                        test.assertEqual(bottomY, options.getBottomY());
                    });
                };

                setBottomYErrorTest.run("with existing topY value set",
                    DrawTextOptions.create().setTopY(2),
                    new PreConditionFailure("this.hasY() cannot be true."));
                setBottomYErrorTest.run("with existing centerY value set",
                    DrawTextOptions.create().setCenterY(2),
                    new PreConditionFailure("this.hasY() cannot be true."));
                setBottomYErrorTest.run("with existing baselineY value set",
                    DrawTextOptions.create().setBaselineY(2),
                    new PreConditionFailure("this.hasY() cannot be true."));
                setBottomYErrorTest.run("with existing bottomY value set",
                    DrawTextOptions.create().setBottomY(2),
                    new PreConditionFailure("this.hasY() cannot be true."));

                final Action3<String,DrawTextOptions,Integer> setBottomYTest = (String testName, DrawTextOptions options, Integer bottomY) ->
                {
                    runner.test(testName, (Test test) ->
                    {
                        final DrawTextOptions setBottomYResult = options.setBottomY(bottomY);
                        test.assertSame(options, setBottomYResult);
                        test.assertEqual(bottomY, options.getBottomY());
                        test.assertNull(options.getTopY());
                        test.assertNull(options.getCenterY());
                        test.assertNull(options.getBaselineY());
                    });
                };

                setBottomYTest.run("with no y-value set",
                    DrawTextOptions.create(),
                    20);
            });

            runner.testGroup("setText(String)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final DrawTextOptions options = DrawTextOptions.create();
                    test.assertThrows(() -> options.setText(null),
                        new PreConditionFailure("text cannot be null."));
                    test.assertNull(options.getText());
                });

                final Action1<String> setTextTest = (String text) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(text), (Test test) ->
                    {
                        final DrawTextOptions options = DrawTextOptions.create();
                        final DrawTextOptions setTextResult = options.setText(text);
                        test.assertSame(options, setTextResult);
                        test.assertEqual(text, options.getText());
                    });
                };

                setTextTest.run("");
                setTextTest.run("Hello!");
            });

            runner.testGroup("toJson()", () ->
            {
                final Action2<DrawTextOptions,JSONObject> toJsonTest = (DrawTextOptions options, JSONObject expected) ->
                {
                    runner.test("with " + options.toString(), (Test test) ->
                    {
                        test.assertEqual(expected, options.toJson());
                    });
                };

                toJsonTest.run(
                    DrawTextOptions.create(),
                    JSONObject.create());
                toJsonTest.run(
                    DrawTextOptions.create()
                        .setLeftX(1),
                    JSONObject.create()
                        .setNumber("leftX", 1));
                toJsonTest.run(
                    DrawTextOptions.create()
                        .setCenterX(2),
                    JSONObject.create()
                        .setNumber("centerX", 2));
                toJsonTest.run(
                    DrawTextOptions.create()
                        .setRightX(3),
                    JSONObject.create()
                        .setNumber("rightX", 3));
                toJsonTest.run(
                    DrawTextOptions.create()
                        .setTopY(4),
                    JSONObject.create()
                        .setNumber("topY", 4));
                toJsonTest.run(
                    DrawTextOptions.create()
                        .setCenterY(5),
                    JSONObject.create()
                        .setNumber("centerY", 5));
                toJsonTest.run(
                    DrawTextOptions.create()
                        .setBaselineY(6),
                    JSONObject.create()
                        .setNumber("baselineY", 6));
                toJsonTest.run(
                    DrawTextOptions.create()
                        .setBottomY(7),
                    JSONObject.create()
                        .setNumber("bottomY", 7));
                toJsonTest.run(
                    DrawTextOptions.create()
                        .setText(""),
                    JSONObject.create()
                        .setString("text", ""));
                toJsonTest.run(
                    DrawTextOptions.create()
                        .setLeftX(10)
                        .setCenterY(50)
                        .setText("Hi!"),
                    JSONObject.create()
                        .setNumber("leftX", 10)
                        .setNumber("centerY", 50)
                        .setString("text", "Hi!"));
            });

            runner.testGroup("toString()", () ->
            {
                final Action2<DrawTextOptions,String> toJsonTest = (DrawTextOptions options, String expected) ->
                {
                    runner.test("with " + options.toString(), (Test test) ->
                    {
                        test.assertEqual(expected, options.toString());
                    });
                };

                toJsonTest.run(
                    DrawTextOptions.create(),
                    "{}");
                toJsonTest.run(
                    DrawTextOptions.create()
                        .setLeftX(1),
                    "{\"leftX\":1}");
                toJsonTest.run(
                    DrawTextOptions.create()
                        .setCenterX(2),
                    "{\"centerX\":2}");
                toJsonTest.run(
                    DrawTextOptions.create()
                        .setRightX(3),
                    "{\"rightX\":3}");
                toJsonTest.run(
                    DrawTextOptions.create()
                        .setTopY(4),
                    "{\"topY\":4}");
                toJsonTest.run(
                    DrawTextOptions.create()
                        .setCenterY(5),
                    "{\"centerY\":5}");
                toJsonTest.run(
                    DrawTextOptions.create()
                        .setBaselineY(6),
                    "{\"baselineY\":6}");
                toJsonTest.run(
                    DrawTextOptions.create()
                        .setBottomY(7),
                    "{\"bottomY\":7}");
                toJsonTest.run(
                    DrawTextOptions.create()
                        .setText(""),
                    "{\"text\":\"\"}");
                toJsonTest.run(
                    DrawTextOptions.create()
                        .setLeftX(10)
                        .setCenterY(50)
                        .setText("Hi!"),
                    "{\"leftX\":10,\"centerY\":50,\"text\":\"Hi!\"}");
            });

            runner.testGroup("equals(Object)", () ->
            {
                final Action3<DrawTextOptions,Object,Boolean> equalsTest = (DrawTextOptions lhs, Object rhs, Boolean expected) ->
                {
                    runner.test("with " + English.andList(lhs, rhs), (Test test) ->
                    {
                        test.assertEqual(expected, lhs.equals(rhs));
                    });
                };

                equalsTest.run(
                    DrawTextOptions.create(),
                    null,
                    false);
                equalsTest.run(
                    DrawTextOptions.create(),
                    DrawLineOptions.create(),
                    false);
                equalsTest.run(
                    DrawTextOptions.create(),
                    DrawTextOptions.create(),
                    true);
                equalsTest.run(
                    DrawTextOptions.create()
                        .setLeftX(1),
                    DrawTextOptions.create()
                        .setLeftX(2),
                    false);
                equalsTest.run(
                    DrawTextOptions.create()
                        .setLeftX(1),
                    DrawTextOptions.create()
                        .setLeftX(1),
                    true);
                equalsTest.run(
                    DrawTextOptions.create()
                        .setCenterX(1),
                    DrawTextOptions.create()
                        .setCenterX(2),
                    false);
                equalsTest.run(
                    DrawTextOptions.create()
                        .setCenterX(1),
                    DrawTextOptions.create()
                        .setCenterX(1),
                    true);
                equalsTest.run(
                    DrawTextOptions.create()
                        .setRightX(1),
                    DrawTextOptions.create()
                        .setRightX(2),
                    false);
                equalsTest.run(
                    DrawTextOptions.create()
                        .setRightX(1),
                    DrawTextOptions.create()
                        .setRightX(1),
                    true);
                equalsTest.run(
                    DrawTextOptions.create()
                        .setTopY(1),
                    DrawTextOptions.create()
                        .setTopY(2),
                    false);
                equalsTest.run(
                    DrawTextOptions.create()
                        .setTopY(1),
                    DrawTextOptions.create()
                        .setTopY(1),
                    true);
                equalsTest.run(
                    DrawTextOptions.create()
                        .setCenterY(1),
                    DrawTextOptions.create()
                        .setCenterY(2),
                    false);
                equalsTest.run(
                    DrawTextOptions.create()
                        .setCenterY(1),
                    DrawTextOptions.create()
                        .setCenterY(1),
                    true);
                equalsTest.run(
                    DrawTextOptions.create()
                        .setBaselineY(1),
                    DrawTextOptions.create()
                        .setBaselineY(2),
                    false);
                equalsTest.run(
                    DrawTextOptions.create()
                        .setBaselineY(1),
                    DrawTextOptions.create()
                        .setBaselineY(1),
                    true);
                equalsTest.run(
                    DrawTextOptions.create()
                        .setBottomY(1),
                    DrawTextOptions.create()
                        .setBottomY(2),
                    false);
                equalsTest.run(
                    DrawTextOptions.create()
                        .setBottomY(1),
                    DrawTextOptions.create()
                        .setBottomY(1),
                    true);
                equalsTest.run(
                    DrawTextOptions.create()
                        .setText(""),
                    DrawTextOptions.create()
                        .setText("abc"),
                    false);
                equalsTest.run(
                    DrawTextOptions.create()
                        .setText("abc"),
                    DrawTextOptions.create()
                        .setText("abc"),
                    true);
            });

            runner.testGroup("equals(DrawTextOptions)", () ->
            {
                final Action3<DrawTextOptions,DrawTextOptions,Boolean> equalsTest = (DrawTextOptions lhs, DrawTextOptions rhs, Boolean expected) ->
                {
                    runner.test("with " + English.andList(lhs, rhs), (Test test) ->
                    {
                        test.assertEqual(expected, lhs.equals(rhs));
                    });
                };

                equalsTest.run(
                    DrawTextOptions.create(),
                    null,
                    false);
                equalsTest.run(
                    DrawTextOptions.create(),
                    DrawTextOptions.create(),
                    true);
                equalsTest.run(
                    DrawTextOptions.create()
                        .setLeftX(1),
                    DrawTextOptions.create()
                        .setLeftX(2),
                    false);
                equalsTest.run(
                    DrawTextOptions.create()
                        .setLeftX(1),
                    DrawTextOptions.create()
                        .setLeftX(1),
                    true);
                equalsTest.run(
                    DrawTextOptions.create()
                        .setCenterX(1),
                    DrawTextOptions.create()
                        .setCenterX(2),
                    false);
                equalsTest.run(
                    DrawTextOptions.create()
                        .setCenterX(1),
                    DrawTextOptions.create()
                        .setCenterX(1),
                    true);
                equalsTest.run(
                    DrawTextOptions.create()
                        .setRightX(1),
                    DrawTextOptions.create()
                        .setRightX(2),
                    false);
                equalsTest.run(
                    DrawTextOptions.create()
                        .setRightX(1),
                    DrawTextOptions.create()
                        .setRightX(1),
                    true);
                equalsTest.run(
                    DrawTextOptions.create()
                        .setTopY(1),
                    DrawTextOptions.create()
                        .setTopY(2),
                    false);
                equalsTest.run(
                    DrawTextOptions.create()
                        .setTopY(1),
                    DrawTextOptions.create()
                        .setTopY(1),
                    true);
                equalsTest.run(
                    DrawTextOptions.create()
                        .setCenterY(1),
                    DrawTextOptions.create()
                        .setCenterY(2),
                    false);
                equalsTest.run(
                    DrawTextOptions.create()
                        .setCenterY(1),
                    DrawTextOptions.create()
                        .setCenterY(1),
                    true);
                equalsTest.run(
                    DrawTextOptions.create()
                        .setBaselineY(1),
                    DrawTextOptions.create()
                        .setBaselineY(2),
                    false);
                equalsTest.run(
                    DrawTextOptions.create()
                        .setBaselineY(1),
                    DrawTextOptions.create()
                        .setBaselineY(1),
                    true);
                equalsTest.run(
                    DrawTextOptions.create()
                        .setBottomY(1),
                    DrawTextOptions.create()
                        .setBottomY(2),
                    false);
                equalsTest.run(
                    DrawTextOptions.create()
                        .setBottomY(1),
                    DrawTextOptions.create()
                        .setBottomY(1),
                    true);
                equalsTest.run(
                    DrawTextOptions.create()
                        .setText(""),
                    DrawTextOptions.create()
                        .setText("abc"),
                    false);
                equalsTest.run(
                    DrawTextOptions.create()
                        .setText("abc"),
                    DrawTextOptions.create()
                        .setText("abc"),
                    true);
            });
        });
    }
}
