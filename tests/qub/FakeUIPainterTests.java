package qub;

public interface FakeUIPainterTests
{
    public static void test(TestRunner runner)
    {
        runner.testGroup(FakeUIPainter.class, () ->
        {
            final Function0<FakeUIPainter> creator = FakeUIPainter::create;

            UIPainterTests.test(runner, creator);

            runner.test("drawLine(int,int,int,int)", (Test test) ->
            {
                final FakeUIPainter painter = creator.run();

                final FakeUIPainter drawLineResult = painter.drawLine(1, 2, 3, 4);
                test.assertSame(painter, drawLineResult);

                test.assertEqual(
                    Iterable.create(
                        FakeDrawLineOperation.create()
                            .setStart(1, 2)
                            .setEnd(3, 4)
                            .setLineColor(painter.getLineColor())),
                    painter.getDrawOperations());
            });

            runner.testGroup("drawLine(Point2Integer,Point2Integer)", () ->
            {
                final Action2<Point2Integer,Point2Integer> drawLineTest = (Point2Integer start, Point2Integer end) ->
                {
                    runner.test("with " + English.andList(start, end), (Test test) ->
                    {
                        final FakeUIPainter painter = creator.run();

                        final FakeUIPainter drawLineResult = painter.drawLine(Point2.create(1, 2), Point2.create(3, 4));
                        test.assertSame(painter, drawLineResult);

                        test.assertEqual(
                            Iterable.create(
                                FakeDrawLineOperation.create()
                                    .setStart(start)
                                    .setEnd(end)
                                    .setLineColor(painter.getLineColor())),
                            painter.getDrawOperations());
                    });
                };

                drawLineTest.run(Point2.create(1, 2), Point2.create(3, 4));
            });

            runner.testGroup("drawLine(DrawLineOptions)", () ->
            {
                final Action2<DrawLineOptions,FakeDrawLineOperation> drawLineTest = (DrawLineOptions options, FakeDrawLineOperation expected) ->
                {
                    runner.test("with " + options.toString(), (Test test) ->
                    {
                        final FakeUIPainter painter = FakeUIPainter.create();
                        final Color lineColor = painter.getLineColor();

                        final FakeUIPainter drawLineResult = painter.drawLine(options);
                        test.assertSame(painter, drawLineResult);

                        test.assertEqual(
                            Iterable.create(expected),
                            painter.getDrawOperations());
                        test.assertEqual(lineColor, painter.getLineColor());
                    });
                };

                drawLineTest.run(
                    DrawLineOptions.create()
                        .setStart(1, 2)
                        .setEnd(3, 4),
                    FakeDrawLineOperation.create()
                        .setStart(1, 2)
                        .setEnd(3, 4)
                        .setLineColor(Color.black));
                drawLineTest.run(
                    DrawLineOptions.create()
                        .setStart(1, 2)
                        .setEnd(3, 4)
                        .setLineColor(Color.red),
                    FakeDrawLineOperation.create()
                        .setStart(1, 2)
                        .setEnd(3, 4)
                        .setLineColor(Color.red));
            });

            runner.testGroup("drawOval(int,int,int,int)", () ->
            {
                final Action4<Integer,Integer,Integer,Integer> drawOvalTest = (Integer leftX, Integer topY, Integer width, Integer height) ->
                {
                    runner.test("with " + English.andList(leftX, topY, width, height), (Test test) ->
                    {
                        final FakeUIPainter painter = creator.run();

                        final FakeUIPainter drawOvalResult = painter.drawOval(leftX, topY, width, height);
                        test.assertSame(painter, drawOvalResult);

                        test.assertEqual(
                            Iterable.create(
                                FakeDrawOvalOperation.create()
                                    .setLeftX(leftX)
                                    .setTopY(topY)
                                    .setWidth(width)
                                    .setHeight(height)
                                    .setLineColor(painter.getLineColor())
                                    .setFillColor(painter.getFillColor())),
                            painter.getDrawOperations());
                    });
                };

                drawOvalTest.run(1, 2, 3, 4);
            });

            runner.testGroup("drawOval(DrawOvalOptions)", () ->
            {
                final Action2<DrawOvalOptions,FakeDrawOvalOperation> drawOvalTest = (DrawOvalOptions options, FakeDrawOvalOperation expected) ->
                {
                    runner.test("with " + options.toString(), (Test test) ->
                    {
                        final FakeUIPainter painter = FakeUIPainter.create();
                        final Color lineColor = painter.getLineColor();
                        final Color fillColor = painter.getFillColor();

                        final FakeUIPainter drawOvalResult = painter.drawOval(options);
                        test.assertSame(painter, drawOvalResult);

                        test.assertEqual(
                            Iterable.create(expected),
                            painter.getDrawOperations());
                        test.assertEqual(lineColor, painter.getLineColor());
                        test.assertEqual(fillColor, painter.getFillColor());
                    });
                };

                drawOvalTest.run(
                    DrawOvalOptions.create()
                        .setLeftX(1)
                        .setTopY(2)
                        .setWidth(3)
                        .setHeight(4),
                    FakeDrawOvalOperation.create()
                        .setLeftX(1)
                        .setTopY(2)
                        .setWidth(3)
                        .setHeight(4)
                        .setLineColor(Color.black)
                        .setFillColor(Color.transparent));
                drawOvalTest.run(
                    DrawOvalOptions.create()
                        .setLeftX(1)
                        .setTopY(2)
                        .setWidth(3)
                        .setHeight(4)
                        .setLineColor(Color.red),
                    FakeDrawOvalOperation.create()
                        .setLeftX(1)
                        .setTopY(2)
                        .setWidth(3)
                        .setHeight(4)
                        .setLineColor(Color.red)
                        .setFillColor(Color.transparent));
                drawOvalTest.run(
                    DrawOvalOptions.create()
                        .setLeftX(1)
                        .setTopY(2)
                        .setWidth(3)
                        .setHeight(4)
                        .setLineColor(Color.transparent)
                        .setFillColor(Color.green),
                    FakeDrawOvalOperation.create()
                        .setLeftX(1)
                        .setTopY(2)
                        .setWidth(3)
                        .setHeight(4)
                        .setLineColor(Color.transparent)
                        .setFillColor(Color.green));
            });

            runner.testGroup("drawRectangle(int,int,int,int)", () ->
            {
                final Action4<Integer,Integer,Integer,Integer> drawRectangleTest = (Integer leftX, Integer topY, Integer width, Integer height) ->
                {
                    runner.test("with " + English.andList(leftX, topY, width, height), (Test test) ->
                    {
                        final FakeUIPainter painter = creator.run();

                        final FakeUIPainter drawRectangleResult = painter.drawRectangle(leftX, topY, width, height);
                        test.assertSame(painter, drawRectangleResult);

                        test.assertEqual(
                            Iterable.create(
                                FakeDrawRectangleOperation.create()
                                    .setLeftX(leftX)
                                    .setTopY(topY)
                                    .setWidth(width)
                                    .setHeight(height)
                                    .setLineColor(painter.getLineColor())
                                    .setFillColor(painter.getFillColor())),
                            painter.getDrawOperations());
                    });
                };

                drawRectangleTest.run(1, 2, 3, 4);
            });

            runner.testGroup("drawRectangle(DrawRectangleOptions)", () ->
            {
                final Action2<DrawRectangleOptions,FakeDrawRectangleOperation> drawRectangleTest = (DrawRectangleOptions options, FakeDrawRectangleOperation expected) ->
                {
                    runner.test("with " + options.toString(), (Test test) ->
                    {
                        final FakeUIPainter painter = FakeUIPainter.create();
                        final Color lineColor = painter.getLineColor();
                        final Color fillColor = painter.getFillColor();

                        final FakeUIPainter drawRectangleResult = painter.drawRectangle(options);
                        test.assertSame(painter, drawRectangleResult);

                        test.assertEqual(
                            Iterable.create(expected),
                            painter.getDrawOperations());
                        test.assertEqual(lineColor, painter.getLineColor());
                        test.assertEqual(fillColor, painter.getFillColor());
                    });
                };

                drawRectangleTest.run(
                    DrawRectangleOptions.create()
                        .setLeftX(1)
                        .setTopY(2)
                        .setWidth(3)
                        .setHeight(4),
                    FakeDrawRectangleOperation.create()
                        .setLeftX(1)
                        .setTopY(2)
                        .setWidth(3)
                        .setHeight(4)
                        .setLineColor(Color.black)
                        .setFillColor(Color.transparent));
                drawRectangleTest.run(
                    DrawRectangleOptions.create()
                        .setLeftX(1)
                        .setTopY(2)
                        .setWidth(3)
                        .setHeight(4)
                        .setLineColor(Color.red),
                    FakeDrawRectangleOperation.create()
                        .setLeftX(1)
                        .setTopY(2)
                        .setWidth(3)
                        .setHeight(4)
                        .setLineColor(Color.red)
                        .setFillColor(Color.transparent));
                drawRectangleTest.run(
                    DrawRectangleOptions.create()
                        .setLeftX(1)
                        .setTopY(2)
                        .setWidth(3)
                        .setHeight(4)
                        .setLineColor(Color.transparent)
                        .setFillColor(Color.green),
                    FakeDrawRectangleOperation.create()
                        .setLeftX(1)
                        .setTopY(2)
                        .setWidth(3)
                        .setHeight(4)
                        .setLineColor(Color.transparent)
                        .setFillColor(Color.green));
            });

            runner.testGroup("drawText(int,int,String)", () ->
            {
                final Action3<Integer,Integer,String> drawTextTest = (Integer leftX, Integer baselineY, String text) ->
                {
                    runner.test("with " + English.andList(leftX, baselineY, Strings.escapeAndQuote(text)), (Test test) ->
                    {
                        final FakeUIPainter painter = FakeUIPainter.create();
                        final FakeUIPainter drawTextResult = painter.drawText(leftX, baselineY, text);
                        test.assertSame(painter, drawTextResult);
                        test.assertEqual(
                            Iterable.create(
                                FakeDrawTextOperation.create()
                                    .setLeftX(leftX)
                                    .setBaselineY(baselineY)
                                    .setText(text)
                                    .setLineColor(painter.getLineColor())),
                            painter.getDrawOperations());
                    });
                };

                drawTextTest.run(0, 0, "hello");
                drawTextTest.run(-19, -100, "hello there");
            });

            runner.testGroup("drawText(DrawTextOptions)", () ->
            {
                runner.test("with transparent painter line color and ignoring transparent draws", (Test test) ->
                {
                    final FakeUIPainter painter = FakeUIPainter.create()
                        .setLineColor(Color.transparent)
                        .setIgnoreTransparentDraws(true);

                    final FakeUIPainter drawTextResult = painter.drawText(
                        DrawTextOptions.create()
                            .setLeftX(1)
                            .setBottomY(5)
                            .setText("hello"));
                    test.assertSame(painter, drawTextResult);
                    test.assertEqual(
                        Iterable.create(),
                        painter.getDrawOperations());
                });

                runner.test("with transparent options line color and ignoring transparent draws", (Test test) ->
                {
                    final FakeUIPainter painter = FakeUIPainter.create()
                        .setIgnoreTransparentDraws(true);

                    final FakeUIPainter drawTextResult = painter.drawText(
                        DrawTextOptions.create()
                            .setLeftX(1)
                            .setBottomY(5)
                            .setText("hello")
                            .setLineColor(Color.transparent));
                    test.assertSame(painter, drawTextResult);
                    test.assertEqual(
                        Iterable.create(),
                        painter.getDrawOperations());
                });

                final Action2<DrawTextOptions,FakeDrawTextOperation> drawTextTest = (DrawTextOptions options, FakeDrawTextOperation expected) ->
                {
                    runner.test("with " + options, (Test test) ->
                    {
                        final FakeUIPainter painter = FakeUIPainter.create();
                        final FakeUIPainter drawTextResult = painter.drawText(options);
                        test.assertSame(painter, drawTextResult);
                        test.assertEqual(
                            Iterable.create(expected),
                            painter.getDrawOperations());
                    });
                };

                drawTextTest.run(
                    DrawTextOptions.create()
                        .setLeftX(1)
                        .setTopY(2)
                        .setText("c"),
                    FakeDrawTextOperation.create()
                        .setLeftX(1)
                        .setTopY(2)
                        .setText("c")
                        .setLineColor(Color.black));
                drawTextTest.run(
                    DrawTextOptions.create()
                        .setCenterX(1)
                        .setCenterY(2)
                        .setText("c")
                        .setLineColor(Color.red),
                    FakeDrawTextOperation.create()
                        .setCenterX(1)
                        .setCenterY(2)
                        .setText("c")
                        .setLineColor(Color.red));
                drawTextTest.run(
                    DrawTextOptions.create()
                        .setRightX(1)
                        .setBaselineY(2)
                        .setText("c"),
                    FakeDrawTextOperation.create()
                        .setRightX(1)
                        .setBaselineY(2)
                        .setText("c")
                        .setLineColor(Color.black));
                drawTextTest.run(
                    DrawTextOptions.create()
                        .setRightX(1)
                        .setBottomY(2)
                        .setText("c"),
                    FakeDrawTextOperation.create()
                        .setRightX(1)
                        .setBottomY(2)
                        .setText("c")
                        .setLineColor(Color.black));
            });
        });
    }
}
