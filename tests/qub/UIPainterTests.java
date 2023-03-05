package qub;

public interface UIPainterTests
{
    public static void test(TestRunner runner, Function0<? extends UIPainter> creator)
    {
        runner.testGroup(UIPainter.class, () ->
        {
            runner.testGroup("setFillColor(Color)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final UIPainter painter = creator.run();
                    final Color fillColor = painter.getFillColor();

                    test.assertThrows(() -> painter.setFillColor(null),
                        new PreConditionFailure("fillColor cannot be null."));

                    test.assertEqual(fillColor, painter.getFillColor());
                });

                final Action1<Color> setFillColorTest = (Color fillColor) ->
                {
                    runner.test("with " + fillColor, (Test test) ->
                    {
                        final UIPainter painter = creator.run();
                        final UIPainter setFillColorResult = painter.setFillColor(fillColor);
                        test.assertSame(painter, setFillColorResult);
                        test.assertEqual(fillColor, painter.getFillColor());
                    });
                };

                setFillColorTest.run(Color.red);
                setFillColorTest.run(Color.white);
                setFillColorTest.run(Color.transparent);
                setFillColorTest.run(Color.create(1, 2, 3));
                setFillColorTest.run(Color.create(1, 2, 3, 4));
            });

            runner.testGroup("setLineColor(Color)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final UIPainter painter = creator.run();
                    final Color lineColor = painter.getLineColor();

                    test.assertThrows(() -> painter.setLineColor(null),
                        new PreConditionFailure("lineColor cannot be null."));

                    test.assertEqual(lineColor, painter.getLineColor());
                });

                final Action1<Color> setLineColorTest = (Color lineColor) ->
                {
                    runner.test("with " + lineColor, (Test test) ->
                    {
                        final UIPainter painter = creator.run();
                        final UIPainter setLineColorResult = painter.setLineColor(lineColor);
                        test.assertSame(painter, setLineColorResult);
                        test.assertEqual(lineColor, painter.getLineColor());
                    });
                };

                setLineColorTest.run(Color.red);
                setLineColorTest.run(Color.white);
                setLineColorTest.run(Color.transparent);
                setLineColorTest.run(Color.create(1, 2, 3));
                setLineColorTest.run(Color.create(1, 2, 3, 4));
            });

            runner.testGroup("drawLine(Point2Integer,Point2Integer)", () ->
            {
                final Action3<Point2Integer,Point2Integer,Throwable> drawLineErrorTest = (Point2Integer start, Point2Integer end, Throwable expected) ->
                {
                    runner.test("with " + English.andList(start, end), (Test test) ->
                    {
                        final UIPainter painter = creator.run();

                        test.assertThrows(() -> painter.drawLine(start, end),
                            expected);
                    });
                };

                drawLineErrorTest.run(null, null, new PreConditionFailure("start cannot be null."));
                drawLineErrorTest.run(null, Point2.create(3, 4), new PreConditionFailure("start cannot be null."));
                drawLineErrorTest.run(Point2.create(1, 2), null, new PreConditionFailure("end cannot be null."));
            });

            runner.testGroup("drawLine(DrawLineOptions)", () ->
            {
                final Action2<DrawLineOptions,Throwable> drawLineErrorTest = (DrawLineOptions options, Throwable expected) ->
                {
                    runner.test("with " + options, (Test test) ->
                    {
                        final UIPainter painter = creator.run();

                        test.assertThrows(() -> painter.drawLine(options),
                            expected);
                    });
                };

                drawLineErrorTest.run(
                    null,
                    new PreConditionFailure("options cannot be null."));
            });

            runner.testGroup("drawOval(int,int,int,int)", () ->
            {
                final Action5<Integer,Integer,Integer,Integer,Throwable> drawOvalErrorTest = (Integer leftX, Integer topY, Integer width, Integer height, Throwable expected) ->
                {
                    runner.test("with " + English.andList(leftX, topY, width, height), (Test test) ->
                    {
                        final UIPainter painter = creator.run();

                        test.assertThrows(() -> painter.drawOval(leftX, topY, width, height),
                            expected);
                    });
                };

                drawOvalErrorTest.run(1, 2, -1, 4, new PreConditionFailure("width (-1) must be greater than or equal to 0."));
                drawOvalErrorTest.run(1, 2, 3, -1, new PreConditionFailure("height (-1) must be greater than or equal to 0."));
            });

            runner.testGroup("drawOval(DrawOvalOptions)", () ->
            {
                final Action2<DrawOvalOptions,Throwable> drawOvalErrorTest = (DrawOvalOptions options, Throwable expected) ->
                {
                    runner.test("with " + options, (Test test) ->
                    {
                        final UIPainter painter = creator.run();

                        test.assertThrows(() -> painter.drawOval(options),
                            expected);
                    });
                };

                drawOvalErrorTest.run(
                    null,
                    new PreConditionFailure("options cannot be null."));
            });

            runner.testGroup("drawRectangle(int,int,int,int)", () ->
            {
                final Action5<Integer,Integer,Integer,Integer,Throwable> drawRectangleErrorTest = (Integer leftX, Integer topY, Integer width, Integer height, Throwable expected) ->
                {
                    runner.test("with " + English.andList(leftX, topY, width, height), (Test test) ->
                    {
                        final UIPainter painter = creator.run();

                        test.assertThrows(() -> painter.drawRectangle(leftX, topY, width, height),
                            expected);
                    });
                };

                drawRectangleErrorTest.run(1, 2, -1, 4, new PreConditionFailure("width (-1) must be greater than or equal to 0."));
                drawRectangleErrorTest.run(1, 2, 3, -1, new PreConditionFailure("height (-1) must be greater than or equal to 0."));
            });

            runner.testGroup("drawRectangle(DrawRectangleOptions)", () ->
            {
                final Action2<DrawRectangleOptions,Throwable> drawRectangleErrorTest = (DrawRectangleOptions options, Throwable expected) ->
                {
                    runner.test("with " + options, (Test test) ->
                    {
                        final UIPainter painter = creator.run();

                        test.assertThrows(() -> painter.drawRectangle(options),
                            expected);
                    });
                };

                drawRectangleErrorTest.run(
                    null,
                    new PreConditionFailure("options cannot be null."));
            });

            runner.testGroup("drawText(int,int,String)", () ->
            {
                final Action4<Integer,Integer,String,Throwable> drawTextErrorTest = (Integer leftX, Integer baselineY, String text, Throwable expected) ->
                {
                    runner.test("with " + English.andList(leftX, baselineY, Strings.escapeAndQuote(text)), (Test test) ->
                    {
                        final UIPainter painter = creator.run();

                        test.assertThrows(() -> painter.drawText(leftX, baselineY, text),
                            expected);
                    });
                };

                drawTextErrorTest.run(1, 2, null, new PreConditionFailure("text cannot be null."));
            });

            runner.testGroup("drawText(DrawTextOptions)", () ->
            {
                final Action2<DrawTextOptions,Throwable> drawTextErrorTest = (DrawTextOptions options, Throwable expected) ->
                {
                    runner.test("with " + options, (Test test) ->
                    {
                        final UIPainter painter = creator.run();

                        test.assertThrows(() -> painter.drawText(options),
                            expected);
                    });
                };

                drawTextErrorTest.run(
                    null,
                    new PreConditionFailure("options cannot be null."));
                drawTextErrorTest.run(
                    DrawTextOptions.create(),
                    new PreConditionFailure("options.hasX() cannot be false."));
                drawTextErrorTest.run(
                    DrawTextOptions.create()
                        .setLeftX(1),
                    new PreConditionFailure("options.hasY() cannot be false."));
                drawTextErrorTest.run(
                    DrawTextOptions.create()
                        .setLeftX(1)
                        .setTopY(2),
                    new PreConditionFailure("options.getText() cannot be null."));
            });
        });
    }
}
