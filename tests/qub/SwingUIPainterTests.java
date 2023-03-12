package qub;

public interface SwingUIPainterTests
{
    public static CharacterTable toTable(int[] pixels, int width, int height)
    {
        PreCondition.assertNotNull(pixels, "pixels");
        PreCondition.assertGreaterThanOrEqualTo(width, 0, "width");
        PreCondition.assertGreaterThanOrEqualTo(height, 0, "height");
        PreCondition.assertEqual(width * height, pixels.length, "pixels.length");

        final CharacterTable result = CharacterTable.create();
        for (int row = 0; row < height; row++)
        {
            final List<String> rowValues = List.create();
            for (int column = 0; column < width; column++)
            {
                final int index = column + row * width;
                rowValues.add("0x" + Integers.toHexString(pixels[index]));
            }
            result.addRow(rowValues);
        }

        PostCondition.assertNotNull(result, "result");
        PostCondition.assertEqual(height, result.getRows().getCount(), "result.getRows().getCount()");

        return result;
    }

    public static void assertPixelsEqual(Test test, int[] expected, int[] actual, int width, int height)
    {
        PreCondition.assertNotNull(test, "test");
        PreCondition.assertNotNull(expected, "expected");
        PreCondition.assertNotNull(actual, "actual");
        PreCondition.assertEqual(expected.length, actual.length, "actual.length");

        final CharacterTable expectedTable = SwingUIPainterTests.toTable(expected, width, height);
        final CharacterTable actualTable = SwingUIPainterTests.toTable(actual, width, height);
        test.assertEqual(expectedTable, actualTable);
    }

    public static void assertPixelsEqual(Test test, int[] expected, JavaBufferedImage actualImage)
    {
        PreCondition.assertNotNull(test, "test");
        PreCondition.assertNotNull(expected, "expected");
        PreCondition.assertNotNull(actualImage, "actualImage");
        PreCondition.assertEqual(expected.length, actualImage.getWidth() * actualImage.getHeight(), "actualImage.getWidth() * actualImage.getHeight()");

        SwingUIPainterTests.assertPixelsEqual(test, expected, actualImage.getPixels(), actualImage.getWidth(), actualImage.getHeight());
    }

    public static void test(TestRunner runner)
    {
        runner.testGroup(SwingUIPainter.class, () ->
        {
            final Function1<JavaBufferedImage,SwingUIPainter> creator = SwingUIPainter::create;

            UIPainterTests.test(runner, () -> creator.run(JavaBufferedImage.create(1, 1)));

            runner.testGroup("setFillColor(Color)", () ->
            {
                final Action1<Color> setFillColorTest = (Color fillColor) ->
                {
                    runner.test("with " + fillColor.toString(), (Test test) ->
                    {
                        final SwingUIPainter painter = SwingUIPainter.create(JavaBufferedImage.create(5, 5));
                        test.assertEqual(Color.transparent, painter.getFillColor());

                        final SwingUIPainter setFillColorResult = painter.setFillColor(fillColor);
                        test.assertSame(painter, setFillColorResult);
                        test.assertEqual(fillColor, painter.getFillColor());
                    });
                };

                setFillColorTest.run(Color.white);
                setFillColorTest.run(Color.black);
                setFillColorTest.run(Color.transparent);
                setFillColorTest.run(Color.red);
                setFillColorTest.run(Color.green);
                setFillColorTest.run(Color.blue);
                setFillColorTest.run(Color.create(1, 1, 1, 1));
                setFillColorTest.run(Color.create(1, 2, 3, 4));
                setFillColorTest.run(Color.create(6, 6, 6, 6));
            });

            runner.test("saveFillColor()", (Test test) ->
            {
                final SwingUIPainter painter = SwingUIPainter.create(JavaBufferedImage.create(5, 5));
                final Color fillColor1 = painter.getFillColor();
                test.assertEqual(Color.transparent, fillColor1);

                try (final Disposable fillColorState1 = painter.saveFillColor())
                {
                    test.assertNotNull(fillColorState1);

                    painter.setFillColor(Color.red);
                    test.assertEqual(Color.red, painter.getFillColor());

                    try (final Disposable fillColorState2 = painter.saveFillColor())
                    {
                        test.assertNotNull(fillColorState2);

                        painter.setFillColor(Color.green);
                        test.assertEqual(Color.green, painter.getFillColor());
                    }

                    test.assertEqual(Color.red, painter.getFillColor());
                }

                test.assertEqual(Color.transparent, painter.getFillColor());
            });

            runner.testGroup("setLineColor(Color)", () ->
            {
                final Action1<Color> setLineColorTest = (Color lineColor) ->
                {
                    runner.test("with " + lineColor.toString(), (Test test) ->
                    {
                        final SwingUIPainter painter = SwingUIPainter.create(JavaBufferedImage.create(5, 5));
                        test.assertEqual(Color.white, painter.getLineColor());

                        final SwingUIPainter setLineColorResult = painter.setLineColor(lineColor);
                        test.assertSame(painter, setLineColorResult);
                        test.assertEqual(lineColor, painter.getLineColor());
                    });
                };

                setLineColorTest.run(Color.white);
                setLineColorTest.run(Color.black);
                setLineColorTest.run(Color.transparent);
                setLineColorTest.run(Color.red);
                setLineColorTest.run(Color.green);
                setLineColorTest.run(Color.blue);
                setLineColorTest.run(Color.create(1, 1, 1, 1));
                setLineColorTest.run(Color.create(1, 2, 3, 4));
                setLineColorTest.run(Color.create(6, 6, 6, 6));
            });

            runner.test("saveLineColor()", (Test test) ->
            {
                final SwingUIPainter painter = SwingUIPainter.create(JavaBufferedImage.create(5, 5));
                final Color lineColor1 = painter.getLineColor();
                test.assertEqual(Color.white, lineColor1);

                try (final Disposable lineColorState1 = painter.saveLineColor())
                {
                    test.assertNotNull(lineColorState1);

                    painter.setLineColor(Color.red);
                    test.assertEqual(Color.red, painter.getLineColor());

                    try (final Disposable lineColorState2 = painter.saveLineColor())
                    {
                        test.assertNotNull(lineColorState2);

                        painter.setLineColor(Color.green);
                        test.assertEqual(Color.green, painter.getLineColor());
                    }

                    test.assertEqual(Color.red, painter.getLineColor());
                }

                test.assertEqual(Color.white, painter.getLineColor());
            });

            runner.test("saveColors()", (Test test) ->
            {
                final SwingUIPainter painter = SwingUIPainter.create(JavaBufferedImage.create(5, 5));
                test.assertEqual(Color.white, painter.getLineColor());
                test.assertEqual(Color.transparent, painter.getFillColor());

                try (final Disposable colorsState1 = painter.saveColors())
                {
                    test.assertNotNull(colorsState1);

                    painter.setLineColor(Color.red);
                    painter.setFillColor(Color.create(100, 200, 150));
                    test.assertEqual(Color.red, painter.getLineColor());
                    test.assertEqual(Color.create(100, 200, 150), painter.getFillColor());

                    try (final Disposable colorsState2 = painter.saveColors())
                    {
                        test.assertNotNull(colorsState2);

                        painter.setLineColor(Color.green);
                        painter.setFillColor(Color.create(5, 1, 2, 3));
                        test.assertEqual(Color.green, painter.getLineColor());
                        test.assertEqual(Color.create(5, 1, 2, 3), painter.getFillColor());
                    }

                    test.assertEqual(Color.red, painter.getLineColor());
                    test.assertEqual(Color.create(100, 200, 150), painter.getFillColor());
                }

                test.assertEqual(Color.white, painter.getLineColor());
                test.assertEqual(Color.transparent, painter.getFillColor());
            });

            runner.testGroup("drawLine(int,int,int,int)", () ->
            {
                final Action5<Integer,Integer,Integer,Integer,int[]> drawLineTest = (Integer startX, Integer startY, Integer endX, Integer endY, int[] expectedPixels) ->
                {
                    runner.test("with " + English.andList(startX, startY, endX, endY), (Test test) ->
                    {
                        final JavaBufferedImage image = JavaBufferedImage.create(5, 5);
                        final SwingUIPainter painter = creator.run(image);

                        painter.drawLine(startX, startY, endX, endY);

                        SwingUIPainterTests.assertPixelsEqual(test, expectedPixels, image);
                    });
                };

                drawLineTest.run(0, 0, 0, 0, new int[]
                {
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000
                });
                drawLineTest.run(0, 0, 2, 0, new int[]
                {
                    0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000
                });
                drawLineTest.run(-1, -1, 6, 6, new int[]
                {
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF
                });
            });

            runner.testGroup("drawLine(Point2Integer,Point2Integer)", () ->
            {
                final Action3<Point2Integer,Point2Integer,int[]> drawLineTest = (Point2Integer start, Point2Integer end, int[] expectedPixels) ->
                {
                    runner.test("with " + English.andList(start, end), (Test test) ->
                    {
                        final JavaBufferedImage image = JavaBufferedImage.create(5, 5);
                        final SwingUIPainter painter = creator.run(image);

                        painter.drawLine(start, end);

                        SwingUIPainterTests.assertPixelsEqual(test, expectedPixels, image);
                    });
                };

                drawLineTest.run(Point2.create(0, 0), Point2.create(0, 0), new int[]
                {
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000
                });
                drawLineTest.run(Point2.create(0, 0), Point2.create(2, 0), new int[]
                {
                    0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000
                });
                drawLineTest.run(Point2.create(-1, -1), Point2.create(6, 6), new int[]
                {
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF
                });
            });

            runner.testGroup("drawLine(DrawLineOptions)", () ->
            {
                final Action2<DrawLineOptions,int[]> drawLineTest = (DrawLineOptions options, int[] expectedPixels) ->
                {
                    runner.test("with " + options, (Test test) ->
                    {
                        final JavaBufferedImage image = JavaBufferedImage.create(5, 5);
                        final SwingUIPainter painter = creator.run(image);

                        painter.drawLine(options);

                        SwingUIPainterTests.assertPixelsEqual(test, expectedPixels, image);
                    });
                };

                drawLineTest.run(DrawLineOptions.create().setStart(0, 0).setEnd(0, 0), new int[]
                {
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000
                });
                drawLineTest.run(DrawLineOptions.create().setStart(0, 0).setEnd(2, 0), new int[]
                {
                    0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000
                });
                drawLineTest.run(DrawLineOptions.create().setStart(-1, -1).setEnd(6, 6), new int[]
                {
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF
                });
                drawLineTest.run(DrawLineOptions.create().setStart(-1, -1).setEnd(6, 6).setLineColor(Color.red), new int[]
                {
                    0xFFFF0000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0xFFFF0000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0xFFFF0000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0xFFFF0000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0xFFFF0000
                });
                drawLineTest.run(DrawLineOptions.create().setStart(-1, -1).setEnd(6, 6).setLineColor(Color.create(1, 2, 3, 4)), new int[]
                {
                    0x04000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x04000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x04000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x04000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x04000000
                });
                drawLineTest.run(DrawLineOptions.create().setStart(-1, -1).setEnd(6, 6).setLineColor(Color.create(1, 1, 1, 1)), new int[]
                {
                    0x01000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x01000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x01000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x01000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x01000000
                });
                drawLineTest.run(DrawLineOptions.create().setStart(-1, -1).setEnd(6, 6).setLineColor(Color.create(2, 2, 2, 2)), new int[]
                {
                    0x02000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x02000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x02000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x02000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x02000000
                });
                drawLineTest.run(DrawLineOptions.create().setStart(-1, -1).setEnd(6, 6).setLineColor(Color.create(8, 8, 8, 8)), new int[]
                {
                    0x08000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x08000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x08000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x08000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x08000000
                });
                drawLineTest.run(DrawLineOptions.create().setStart(-1, -1).setEnd(6, 6).setLineColor(Color.create(200, 200, 200, 200)), new int[]
                {
                    0xC8C8C8C8, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0xC8C8C8C8, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0xC8C8C8C8, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0xC8C8C8C8, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0xC8C8C8C8
                });
                drawLineTest.run(DrawLineOptions.create().setStart(-1, -1).setEnd(6, 6).setLineColor(Color.create(8, 16, 32, 64)), new int[]
                {
                    0x40081020, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x40081020, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x40081020, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x40081020, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x40081020
                });
            });

            runner.testGroup("drawOval(int,int,int,int)", () ->
            {
                final Action5<Integer,Integer,Integer,Integer,int[]> drawOvalTest = (Integer leftX, Integer topY, Integer width, Integer height, int[] expectedPixels) ->
                {
                    runner.test("with " + English.andList(leftX, topY, width, height), (Test test) ->
                    {
                        final JavaBufferedImage image = JavaBufferedImage.create(5, 5);
                        final SwingUIPainter painter = SwingUIPainter.create(image);

                        painter.drawOval(leftX, topY, width, height);

                        SwingUIPainterTests.assertPixelsEqual(test, expectedPixels, image);
                    });
                };

                drawOvalTest.run(0, 0, 0, 0, new int[]
                {
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000
                });
                drawOvalTest.run(0, 0, 5, 0, new int[]
                {
                    0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000
                });
                drawOvalTest.run(0, 0, 0, 5, new int[]
                {
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000
                });
                drawOvalTest.run(0, 0, 5, 5, new int[]
                {
                    0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0xFFFFFFFF,
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0xFFFFFFFF
                });
                drawOvalTest.run(0, 0, 4, 4, new int[]
                {
                    0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000,
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF,
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF,
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF,
                    0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000
                });
                drawOvalTest.run(1, 1, 1, 1, new int[]
                {
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000
                });
            });

            runner.testGroup("drawOval(DrawOvalOptions)", () ->
            {
                final Action2<DrawOvalOptions,int[]> drawOvalTest = (DrawOvalOptions options, int[] expectedPixels) ->
                {
                    runner.test("with " + options, (Test test) ->
                    {
                        final JavaBufferedImage image = JavaBufferedImage.create(5, 5);
                        final SwingUIPainter painter = SwingUIPainter.create(image);

                        painter.drawOval(options);

                        SwingUIPainterTests.assertPixelsEqual(test, expectedPixels, image);
                    });
                };

                drawOvalTest.run(DrawOvalOptions.create(), new int[]
                {
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000
                });
                drawOvalTest.run(DrawOvalOptions.create().setWidth(5), new int[]
                {
                    0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000
                });
                drawOvalTest.run(DrawOvalOptions.create().setHeight(5), new int[]
                {
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000
                });
                drawOvalTest.run(DrawOvalOptions.create().setSize(5, 5), new int[]
                {
                    0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0xFFFFFFFF,
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0xFFFFFFFF
                });
                drawOvalTest.run(DrawOvalOptions.create().setSize(4, 4), new int[]
                {
                    0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000,
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF,
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF,
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF,
                    0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000
                });
                drawOvalTest.run(DrawOvalOptions.create().setLeftX(1).setTopY(1).setSize(1, 1), new int[]
                {
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000
                });
                drawOvalTest.run(DrawOvalOptions.create().setLeftX(0).setTopY(0).setSize(4, 4).setLineColor(Color.blue).setFillColor(Color.green), new int[]
                {
                    0x00000000, 0xFF0000FF, 0xFF0000FF, 0xFF0000FF, 0x00000000,
                    0xFF0000FF, 0xFF00FF00, 0xFF00FF00, 0xFF00FF00, 0xFF0000FF,
                    0xFF0000FF, 0xFF00FF00, 0xFF00FF00, 0xFF00FF00, 0xFF0000FF,
                    0xFF0000FF, 0xFF00FF00, 0xFF00FF00, 0xFF00FF00, 0xFF0000FF,
                    0x00000000, 0xFF0000FF, 0xFF0000FF, 0xFF0000FF, 0x00000000
                });
                drawOvalTest.run(DrawOvalOptions.create().setLeftX(0).setTopY(0).setSize(4, 4).setLineColor(Color.transparent).setFillColor(Color.green), new int[]
                {
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0xFF00FF00, 0xFF00FF00, 0xFF00FF00, 0x00000000,
                    0xFF00FF00, 0xFF00FF00, 0xFF00FF00, 0xFF00FF00, 0x00000000,
                    0x00000000, 0xFF00FF00, 0xFF00FF00, 0xFF00FF00, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000
                });
                drawOvalTest.run(DrawOvalOptions.create().setLeftX(0).setTopY(0).setSize(4, 4).setLineColor(Color.blue).setFillColor(Color.transparent), new int[]
                {
                    0x00000000, 0xFF0000FF, 0xFF0000FF, 0xFF0000FF, 0x00000000,
                    0xFF0000FF, 0x00000000, 0x00000000, 0x00000000, 0xFF0000FF,
                    0xFF0000FF, 0x00000000, 0x00000000, 0x00000000, 0xFF0000FF,
                    0xFF0000FF, 0x00000000, 0x00000000, 0x00000000, 0xFF0000FF,
                    0x00000000, 0xFF0000FF, 0xFF0000FF, 0xFF0000FF, 0x00000000
                });
            });

            runner.testGroup("drawRectangle(int,int,int,int)", () ->
            {
                final Action5<Integer,Integer,Integer,Integer,int[]> drawRectangleTest = (Integer leftX, Integer topY, Integer width, Integer height, int[] expectedPixels) ->
                {
                    runner.test("with " + English.andList(leftX, topY, width, height), (Test test) ->
                    {
                        final JavaBufferedImage image = JavaBufferedImage.create(5, 5);
                        final SwingUIPainter painter = SwingUIPainter.create(image);

                        painter.drawRectangle(leftX, topY, width, height);

                        SwingUIPainterTests.assertPixelsEqual(test, expectedPixels, image);
                    });
                };

                drawRectangleTest.run(0, 0, 0, 0, new int[]
                {
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000
                });
                drawRectangleTest.run(0, 0, 5, 0, new int[]
                {
                    0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000
                });
                drawRectangleTest.run(0, 0, 5, 1, new int[]
                {
                    0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF,
                    0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000
                });
                drawRectangleTest.run(0, 0, 5, 2, new int[]
                {
                    0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF,
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000
                });
                drawRectangleTest.run(0, 0, 0, 5, new int[]
                {
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000
                });
                drawRectangleTest.run(0, 0, 1, 5, new int[]
                {
                    0xFFFFFFFF, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000,
                    0xFFFFFFFF, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000,
                    0xFFFFFFFF, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000,
                    0xFFFFFFFF, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000,
                    0xFFFFFFFF, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000
                });
                drawRectangleTest.run(0, 0, 5, 5, new int[]
                {
                    0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF,
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000
                });
                drawRectangleTest.run(0, 0, 4, 4, new int[]
                {
                    0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF,
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF,
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF,
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF,
                    0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF
                });
                drawRectangleTest.run(1, 1, 1, 1, new int[]
                {
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000, 0x00000000,
                    0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000
                });
            });

            runner.testGroup("drawRectangle(DrawRectangleOptions)", () ->
            {
                final Action2<DrawRectangleOptions,int[]> drawRectangleTests = (DrawRectangleOptions options, int[] expectedPixels) ->
                {
                    runner.test("with " + options, (Test test) ->
                    {
                        final JavaBufferedImage image = JavaBufferedImage.create(5, 5);
                        final SwingUIPainter painter = SwingUIPainter.create(image);

                        painter.drawRectangle(options);

                        SwingUIPainterTests.assertPixelsEqual(test, expectedPixels, image);
                    });
                };

                drawRectangleTests.run(DrawRectangleOptions.create(), new int[]
                {
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000
                });
                drawRectangleTests.run(DrawRectangleOptions.create().setWidth(5), new int[]
                {
                    0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000
                });
                drawRectangleTests.run(DrawRectangleOptions.create().setHeight(5), new int[]
                {
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000
                });
                drawRectangleTests.run(DrawRectangleOptions.create().setSize(5, 5), new int[]
                {
                    0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF,
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000
                });
                drawRectangleTests.run(DrawRectangleOptions.create().setSize(4, 4), new int[]
                {
                    0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF,
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF,
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF,
                    0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF,
                    0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF
                });
                drawRectangleTests.run(DrawRectangleOptions.create().setLeftX(1).setTopY(1).setSize(1, 1), new int[]
                {
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000, 0x00000000,
                    0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000
                });
                drawRectangleTests.run(DrawRectangleOptions.create().setLeftX(0).setTopY(0).setSize(4, 4).setLineColor(Color.blue).setFillColor(Color.green), new int[]
                {
                    0xFF0000FF, 0xFF0000FF, 0xFF0000FF, 0xFF0000FF, 0xFF0000FF,
                    0xFF0000FF, 0xFF00FF00, 0xFF00FF00, 0xFF00FF00, 0xFF0000FF,
                    0xFF0000FF, 0xFF00FF00, 0xFF00FF00, 0xFF00FF00, 0xFF0000FF,
                    0xFF0000FF, 0xFF00FF00, 0xFF00FF00, 0xFF00FF00, 0xFF0000FF,
                    0xFF0000FF, 0xFF0000FF, 0xFF0000FF, 0xFF0000FF, 0xFF0000FF
                });
                drawRectangleTests.run(DrawRectangleOptions.create().setLeftX(0).setTopY(0).setSize(4, 4).setLineColor(Color.transparent).setFillColor(Color.green), new int[]
                {
                    0xFF00FF00, 0xFF00FF00, 0xFF00FF00, 0xFF00FF00, 0x00000000,
                    0xFF00FF00, 0xFF00FF00, 0xFF00FF00, 0xFF00FF00, 0x00000000,
                    0xFF00FF00, 0xFF00FF00, 0xFF00FF00, 0xFF00FF00, 0x00000000,
                    0xFF00FF00, 0xFF00FF00, 0xFF00FF00, 0xFF00FF00, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000
                });
                drawRectangleTests.run(DrawRectangleOptions.create().setLeftX(0).setTopY(0).setSize(4, 4).setLineColor(Color.blue).setFillColor(Color.transparent), new int[]
                {
                    0xFF0000FF, 0xFF0000FF, 0xFF0000FF, 0xFF0000FF, 0xFF0000FF,
                    0xFF0000FF, 0x00000000, 0x00000000, 0x00000000, 0xFF0000FF,
                    0xFF0000FF, 0x00000000, 0x00000000, 0x00000000, 0xFF0000FF,
                    0xFF0000FF, 0x00000000, 0x00000000, 0x00000000, 0xFF0000FF,
                    0xFF0000FF, 0xFF0000FF, 0xFF0000FF, 0xFF0000FF, 0xFF0000FF
                });
            });

            runner.testGroup("drawText(int,int,String)", () ->
            {
                final Action5<JavaBufferedImage,Integer,Integer,String,int[]> drawTextTest = (JavaBufferedImage image, Integer leftX, Integer baselineY, String text, int[] expectedPixels) ->
                {
                    runner.test("with " + English.andList(image.getWidth() + "x" + image.getHeight() + " image", leftX, baselineY, Strings.escapeAndQuote(text)), (Test test) ->
                    {
                        final SwingUIPainter painter = SwingUIPainter.create(image);

                        painter.drawText(leftX, baselineY, text);

                        test.assertEqual(expectedPixels, image.getPixels());
                    });
                };

                drawTextTest.run(JavaBufferedImage.create(7, 7), 0, 4, "a", new int[]
                {
                    0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000
                });
                drawTextTest.run(JavaBufferedImage.create(7, 7), 0, 6, "a", new int[]
                {
                    0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000
                });
                drawTextTest.run(JavaBufferedImage.create(7, 10), 0, 6, "a", new int[]
                {
                    0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000
                });
                drawTextTest.run(JavaBufferedImage.create(7, 10), 0, 9, "a", new int[]
                {
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000, 0x00000000,
                    0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000
                });
                drawTextTest.run(JavaBufferedImage.create(10, 13), 0, 9, "a", new int[]
                {
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                });

                drawTextTest.run(JavaBufferedImage.create(10, 13), 2, 11, "a", new int[]
                {
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                });
            });

            runner.testGroup("drawText(DrawTextOptions)", () ->
            {
                final Action3<JavaBufferedImage,DrawTextOptions,int[]> drawTextTest = (JavaBufferedImage image, DrawTextOptions options, int[] expectedPixels) ->
                {
                    runner.test("with " + English.andList(image.getWidth() + "x" + image.getHeight() + " image", options), (Test test) ->
                    {
                        final SwingUIPainter painter = SwingUIPainter.create(image);

                        painter.drawText(options);

                        SwingUIPainterTests.assertPixelsEqual(test, expectedPixels, image);
                    });
                };

                drawTextTest.run(JavaBufferedImage.create(7, 7), DrawTextOptions.create().setLeftX(0).setBaselineY(4).setText("a"), new int[]
                {
                    0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000
                });
                drawTextTest.run(JavaBufferedImage.create(7, 7), DrawTextOptions.create().setLeftX(0).setBaselineY(6).setText("a"), new int[]
                {
                    0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000
                });
                drawTextTest.run(JavaBufferedImage.create(7, 10), DrawTextOptions.create().setLeftX(0).setBaselineY(6).setText("a"), new int[]
                {
                    0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000
                });
                drawTextTest.run(JavaBufferedImage.create(7, 10), DrawTextOptions.create().setLeftX(0).setBaselineY(9).setText("a"), new int[]
                {
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000, 0x00000000,
                    0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000
                });
                drawTextTest.run(JavaBufferedImage.create(10, 13), DrawTextOptions.create().setLeftX(0).setBaselineY(9).setText("a"), new int[]
                {
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                });

                drawTextTest.run(JavaBufferedImage.create(10, 13), DrawTextOptions.create().setLeftX(2).setBaselineY(11).setText("a"), new int[]
                {
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                });
                drawTextTest.run(JavaBufferedImage.create(10, 13), DrawTextOptions.create().setRightX(10).setBottomY(13).setText("a"), new int[]
                {
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF, 0x00000000, 0xFFFFFFFF, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                });
                drawTextTest.run(JavaBufferedImage.create(10, 13), DrawTextOptions.create().setCenterX(5).setTopY(0).setText("a"), new int[]
                {
                    0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,
                    0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,
                    0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,
                    0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,
                    0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,
                    0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,
                    0x00000000,0x00000000,0x00000000,0x00000000,0xFFFFFFFF,0xFFFFFFFF,0xFFFFFFFF,0x00000000,0x00000000,0x00000000,
                    0x00000000,0x00000000,0x00000000,0xFFFFFFFF,0x00000000,0x00000000,0x00000000,0xFFFFFFFF,0x00000000,0x00000000,
                    0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0xFFFFFFFF,0x00000000,0x00000000,
                    0x00000000,0x00000000,0x00000000,0x00000000,0xFFFFFFFF,0xFFFFFFFF,0xFFFFFFFF,0xFFFFFFFF,0x00000000,0x00000000,
                    0x00000000,0x00000000,0x00000000,0xFFFFFFFF,0x00000000,0x00000000,0x00000000,0xFFFFFFFF,0x00000000,0x00000000,
                    0x00000000,0x00000000,0x00000000,0xFFFFFFFF,0x00000000,0x00000000,0xFFFFFFFF,0xFFFFFFFF,0x00000000,0x00000000,
                    0x00000000,0x00000000,0x00000000,0x00000000,0xFFFFFFFF,0xFFFFFFFF,0x00000000,0xFFFFFFFF,0x00000000,0x00000000,
                });
                drawTextTest.run(JavaBufferedImage.create(10, 13), DrawTextOptions.create().setLeftX(0).setCenterY(4).setText("a"), new int[]
                {
                    0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,
                    0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,
                    0x00000000,0x00000000,0xFFFFFFFF,0xFFFFFFFF,0xFFFFFFFF,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,
                    0x00000000,0xFFFFFFFF,0x00000000,0x00000000,0x00000000,0xFFFFFFFF,0x00000000,0x00000000,0x00000000,0x00000000,
                    0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0xFFFFFFFF,0x00000000,0x00000000,0x00000000,0x00000000,
                    0x00000000,0x00000000,0xFFFFFFFF,0xFFFFFFFF,0xFFFFFFFF,0xFFFFFFFF,0x00000000,0x00000000,0x00000000,0x00000000,
                    0x00000000,0xFFFFFFFF,0x00000000,0x00000000,0x00000000,0xFFFFFFFF,0x00000000,0x00000000,0x00000000,0x00000000,
                    0x00000000,0xFFFFFFFF,0x00000000,0x00000000,0xFFFFFFFF,0xFFFFFFFF,0x00000000,0x00000000,0x00000000,0x00000000,
                    0x00000000,0x00000000,0xFFFFFFFF,0xFFFFFFFF,0x00000000,0xFFFFFFFF,0x00000000,0x00000000,0x00000000,0x00000000,
                    0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,
                    0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,
                    0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,
                    0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,0x00000000,
                });
            });

            runner.testGroup("getTextMeasurements(String)", () ->
            {
                final Action2<String,TextMeasurements> getTextMeasurementsTest = (String text, TextMeasurements expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(text), (Test test) ->
                    {
                        final SwingUIPainter painter = SwingUIPainter.create(JavaBufferedImage.create(1, 1));
                        final TextMeasurements measurements = painter.getTextMeasurements(text);
                        test.assertEqual(expected, measurements);
                    });
                };

                getTextMeasurementsTest.run("", TextMeasurements.create()
                    .setAscent(13)
                    .setDescent(3)
                    .setHeight(16)
                    .setWidth(0));
                getTextMeasurementsTest.run("abc", TextMeasurements.create()
                    .setAscent(13)
                    .setDescent(3)
                    .setHeight(16)
                    .setWidth(20));
            });

            runner.testGroup("translateX(int)", () ->
            {
                final Action2<Integer,JSONArray> translateXTest = (Integer x, JSONArray expected) ->
                {
                    runner.test("with " + x, (Test test) ->
                    {
                        final SwingUIPainter painter = SwingUIPainter.create(JavaBufferedImage.create(1, 1));
                        test.assertEqual(Transform2.create(), painter.getTransform());

                        final SwingUIPainter translateXResult = painter.translateX(x);
                        test.assertSame(painter, translateXResult);
                        test.assertEqual(
                            expected,
                            painter.getTransform().toJson());
                    });
                };

                translateXTest.run(-10,
                    JSONArray.create(
                        JSONArray.create().addNumbers(1.0, 0.0, -10.0),
                        JSONArray.create().addNumbers(0.0, 1.0, 0.0),
                        JSONArray.create().addNumbers(0.0, 0.0, 1.0)));
                translateXTest.run(-1,
                    JSONArray.create(
                        JSONArray.create().addNumbers(1.0, 0.0, -1.0),
                        JSONArray.create().addNumbers(0.0, 1.0, 0.0),
                        JSONArray.create().addNumbers(0.0, 0.0, 1.0)));
                translateXTest.run(0,
                    JSONArray.create(
                        JSONArray.create().addNumbers(1.0, 0.0, 0.0),
                        JSONArray.create().addNumbers(0.0, 1.0, 0.0),
                        JSONArray.create().addNumbers(0.0, 0.0, 1.0)));
                translateXTest.run(1,
                    JSONArray.create(
                        JSONArray.create().addNumbers(1.0, 0.0, 1.0),
                        JSONArray.create().addNumbers(0.0, 1.0, 0.0),
                        JSONArray.create().addNumbers(0.0, 0.0, 1.0)));
                translateXTest.run(10,
                    JSONArray.create(
                        JSONArray.create().addNumbers(1.0, 0.0, 10.0),
                        JSONArray.create().addNumbers(0.0, 1.0, 0.0),
                        JSONArray.create().addNumbers(0.0, 0.0, 1.0)));
            });

            runner.testGroup("translateY(int)", () ->
            {
                final Action2<Integer,JSONArray> translateYTest = (Integer y, JSONArray expected) ->
                {
                    runner.test("with " + y, (Test test) ->
                    {
                        final SwingUIPainter painter = SwingUIPainter.create(JavaBufferedImage.create(1, 1));
                        test.assertEqual(Transform2.create(), painter.getTransform());

                        final SwingUIPainter translateYResult = painter.translateY(y);
                        test.assertSame(painter, translateYResult);
                        test.assertEqual(
                            expected,
                            painter.getTransform().toJson());
                    });
                };

                translateYTest.run(-10,
                    JSONArray.create(
                        JSONArray.create().addNumbers(1.0, 0.0, 0.0),
                        JSONArray.create().addNumbers(0.0, 1.0, -10.0),
                        JSONArray.create().addNumbers(0.0, 0.0, 1.0)));
                translateYTest.run(-1,
                    JSONArray.create(
                        JSONArray.create().addNumbers(1.0, 0.0, 0.0),
                        JSONArray.create().addNumbers(0.0, 1.0, -1.0),
                        JSONArray.create().addNumbers(0.0, 0.0, 1.0)));
                translateYTest.run(0,
                    JSONArray.create(
                        JSONArray.create().addNumbers(1.0, 0.0, 0.0),
                        JSONArray.create().addNumbers(0.0, 1.0, 0.0),
                        JSONArray.create().addNumbers(0.0, 0.0, 1.0)));
                translateYTest.run(1,
                    JSONArray.create(
                        JSONArray.create().addNumbers(1.0, 0.0, 0.0),
                        JSONArray.create().addNumbers(0.0, 1.0, 1.0),
                        JSONArray.create().addNumbers(0.0, 0.0, 1.0)));
                translateYTest.run(10,
                    JSONArray.create(
                        JSONArray.create().addNumbers(1.0, 0.0, 0.0),
                        JSONArray.create().addNumbers(0.0, 1.0, 10.0),
                        JSONArray.create().addNumbers(0.0, 0.0, 1.0)));
            });

            runner.testGroup("translate(int,int)", () ->
            {
                final Action3<Integer,Integer,JSONArray> translateTest = (Integer x, Integer y, JSONArray expected) ->
                {
                    runner.test("with " + English.andList(x, y), (Test test) ->
                    {
                        final SwingUIPainter painter = SwingUIPainter.create(JavaBufferedImage.create(1, 1));
                        test.assertEqual(Transform2.create(), painter.getTransform());

                        final SwingUIPainter translateYResult = painter.translate(x, y);
                        test.assertSame(painter, translateYResult);
                        test.assertEqual(
                            expected,
                            painter.getTransform().toJson());
                    });
                };

                translateTest.run(-10, -10,
                    JSONArray.create(
                        JSONArray.create().addNumbers(1.0, 0.0, -10.0),
                        JSONArray.create().addNumbers(0.0, 1.0, -10.0),
                        JSONArray.create().addNumbers(0.0, 0.0, 1.0)));
                translateTest.run(-10, -1,
                    JSONArray.create(
                        JSONArray.create().addNumbers(1.0, 0.0, -10.0),
                        JSONArray.create().addNumbers(0.0, 1.0, -1.0),
                        JSONArray.create().addNumbers(0.0, 0.0, 1.0)));
                translateTest.run(-10, 0,
                    JSONArray.create(
                        JSONArray.create().addNumbers(1.0, 0.0, -10.0),
                        JSONArray.create().addNumbers(0.0, 1.0, 0.0),
                        JSONArray.create().addNumbers(0.0, 0.0, 1.0)));
                translateTest.run(-10, 1,
                    JSONArray.create(
                        JSONArray.create().addNumbers(1.0, 0.0, -10.0),
                        JSONArray.create().addNumbers(0.0, 1.0, 1.0),
                        JSONArray.create().addNumbers(0.0, 0.0, 1.0)));
                translateTest.run(-10, 10,
                    JSONArray.create(
                        JSONArray.create().addNumbers(1.0, 0.0, -10.0),
                        JSONArray.create().addNumbers(0.0, 1.0, 10.0),
                        JSONArray.create().addNumbers(0.0, 0.0, 1.0)));
                translateTest.run(-1, -10,
                    JSONArray.create(
                        JSONArray.create().addNumbers(1.0, 0.0, -1.0),
                        JSONArray.create().addNumbers(0.0, 1.0, -10.0),
                        JSONArray.create().addNumbers(0.0, 0.0, 1.0)));
                translateTest.run(-1, -1,
                    JSONArray.create(
                        JSONArray.create().addNumbers(1.0, 0.0, -1.0),
                        JSONArray.create().addNumbers(0.0, 1.0, -1.0),
                        JSONArray.create().addNumbers(0.0, 0.0, 1.0)));
                translateTest.run(-1, 0,
                    JSONArray.create(
                        JSONArray.create().addNumbers(1.0, 0.0, -1.0),
                        JSONArray.create().addNumbers(0.0, 1.0, 0.0),
                        JSONArray.create().addNumbers(0.0, 0.0, 1.0)));
                translateTest.run(-1, 1,
                    JSONArray.create(
                        JSONArray.create().addNumbers(1.0, 0.0, -1.0),
                        JSONArray.create().addNumbers(0.0, 1.0, 1.0),
                        JSONArray.create().addNumbers(0.0, 0.0, 1.0)));
                translateTest.run(-1, 10,
                    JSONArray.create(
                        JSONArray.create().addNumbers(1.0, 0.0, -1.0),
                        JSONArray.create().addNumbers(0.0, 1.0, 10.0),
                        JSONArray.create().addNumbers(0.0, 0.0, 1.0)));
            });

            runner.testGroup("setTransform(Transform2)", () ->
            {
                final Action1<Transform2> setTransformTest = (Transform2 transform) ->
                {
                    runner.test("with " + transform.toString(), (Test test) ->
                    {
                        final SwingUIPainter painter = SwingUIPainter.create(JavaBufferedImage.create(1, 1));
                        test.assertEqual(Transform2.create(), painter.getTransform());

                        final SwingUIPainter setTransformResult = painter.setTransform(transform);
                        test.assertSame(painter, setTransformResult);
                        test.assertEqual(
                            transform,
                            painter.getTransform());
                    });
                };

                setTransformTest.run(Transform2.create());
                setTransformTest.run(Transform2.create().translate(1, 2));
            });

            runner.test("saveTransform()", (Test test) ->
            {
                final SwingUIPainter painter = SwingUIPainter.create(JavaBufferedImage.create(1, 1));
                final Transform2 transform1 = painter.getTransform();
                test.assertEqual(Transform2.create(), transform1);

                try (final Disposable transformState1 = painter.saveTransform())
                {
                    test.assertNotNull(transformState1);

                    painter.translate(5, 10);
                    final Transform2 transform2 = painter.getTransform();
                    test.assertNotSame(transform1, transform2);
                    test.assertEqual(Transform2.create(), transform1);
                    test.assertEqual(Transform2.create().translate(5, 10), transform2);

                    try (final Disposable transformState2 = painter.saveTransform())
                    {
                        test.assertNotNull(transformState2);

                        painter.translate(-1, -100);
                        final Transform2 transform3 = painter.getTransform();
                        test.assertNotSame(transform1, transform3);
                        test.assertNotSame(transform2, transform3);
                        test.assertEqual(Transform2.create(), transform1);
                        test.assertEqual(Transform2.create().translate(5, 10), transform2);
                        test.assertEqual(Transform2.create().translate(4, -90), transform3);
                    }

                    test.assertEqual(Transform2.create(), transform1);
                    test.assertEqual(Transform2.create().translate(5, 10), transform2);
                    test.assertEqual(transform2, painter.getTransform());
                }

                test.assertEqual(Transform2.create(), transform1);
                test.assertEqual(transform1, painter.getTransform());
            });

            runner.testGroup("setClip(int,int)", () ->
            {
                final Action2<Integer,Integer> setClipTest = (Integer width, Integer height) ->
                {
                    runner.test("with " + English.andList(width, height), (Test test) ->
                    {
                        final SwingUIPainter painter = SwingUIPainter.create(JavaBufferedImage.create(1, 1));
                        test.assertNull(painter.getClip());

                        final SwingUIPainter setClipResult = painter.setClip(width, height);
                        test.assertSame(painter, setClipResult);
                        test.assertEqual(Size2.create(width, height), painter.getClip());
                    });
                };

                setClipTest.run(0, 0);
                setClipTest.run(1, 2);
                setClipTest.run(300, 150);
            });

            runner.testGroup("setClip(Size2Integer)", () ->
            {
                final Action1<Size2Integer> setClipTest = (Size2Integer clip) ->
                {
                    runner.test("with " + clip, (Test test) ->
                    {
                        final SwingUIPainter painter = SwingUIPainter.create(JavaBufferedImage.create(1, 1));
                        test.assertNull(painter.getClip());

                        final SwingUIPainter setClipResult = painter.setClip(clip);
                        test.assertSame(painter, setClipResult);
                        test.assertEqual(clip, painter.getClip());
                    });
                };

                setClipTest.run(Size2.create(0, 0));
                setClipTest.run(Size2.create(1, 2));
                setClipTest.run(Size2.create(300, 150));
            });

            runner.testGroup("setClip(Action1<UIPainterSetClipOptions>)", () ->
            {
                final Action3<Size2Integer,UIPainterSetClipSizeOptions,Size2Integer> setClipTest = (Size2Integer initialClip, UIPainterSetClipSizeOptions options, Size2Integer expected) ->
                {
                    runner.test("with " + English.andList(initialClip, options), (Test test) ->
                    {
                        final SwingUIPainter painter = SwingUIPainter.create(JavaBufferedImage.create(1, 1));
                        test.assertNull(painter.getClip());
                        if (initialClip != null)
                        {
                            painter.setClip(initialClip);
                        }

                        final SwingUIPainter setClipResult = painter.setClip(o -> o
                            .setSize(options.getSize())
                            .setResetClip(options.getResetClip()));
                        test.assertSame(painter, setClipResult);
                        test.assertEqual(expected, painter.getClip());
                    });
                };

                setClipTest.run(
                    null,
                    UIPainterSetClipSizeOptions.create()
                        .setSize(0, 0),
                    Size2.create(0, 0));
                setClipTest.run(
                    null,
                    UIPainterSetClipSizeOptions.create()
                        .setSize(1, 2),
                    Size2.create(1, 2));
                setClipTest.run(
                    null,
                    UIPainterSetClipSizeOptions.create()
                        .setSize(300, 150),
                    Size2.create(300, 150));
                setClipTest.run(
                    Size2.create(100, 200),
                    UIPainterSetClipSizeOptions.create()
                        .setSize(300, 150),
                    Size2.create(100, 150));
                setClipTest.run(
                    Size2.create(100, 200),
                    UIPainterSetClipSizeOptions.create()
                        .setSize(75, 250),
                    Size2.create(75, 200));
                setClipTest.run(
                    Size2.create(100, 200),
                    UIPainterSetClipSizeOptions.create()
                        .setSize(75, 100),
                    Size2.create(75, 100));
                setClipTest.run(
                    Size2.create(100, 200),
                    UIPainterSetClipSizeOptions.create()
                        .setSize(300, 250)
                        .setResetClip(true),
                    Size2.create(300, 250));
            });

            runner.testGroup("setClip(UIPainterSetClipOptions)", () ->
            {
                final Action3<Size2Integer,UIPainterSetClipSizeOptions,Size2Integer> setClipTest = (Size2Integer initialClip, UIPainterSetClipSizeOptions options, Size2Integer expected) ->
                {
                    runner.test("with " + English.andList(initialClip, options), (Test test) ->
                    {
                        final SwingUIPainter painter = SwingUIPainter.create(JavaBufferedImage.create(1, 1));
                        test.assertNull(painter.getClip());
                        if (initialClip != null)
                        {
                            painter.setClip(initialClip);
                        }

                        final SwingUIPainter setClipResult = painter.setClip(options);
                        test.assertSame(painter, setClipResult);
                        test.assertEqual(expected, painter.getClip());
                    });
                };

                setClipTest.run(
                    null,
                    UIPainterSetClipSizeOptions.create()
                        .setSize(0, 0),
                    Size2.create(0, 0));
                setClipTest.run(
                    null,
                    UIPainterSetClipSizeOptions.create()
                        .setSize(1, 2),
                    Size2.create(1, 2));
                setClipTest.run(
                    null,
                    UIPainterSetClipSizeOptions.create()
                        .setSize(300, 150),
                    Size2.create(300, 150));
                setClipTest.run(
                    Size2.create(100, 200),
                    UIPainterSetClipSizeOptions.create()
                        .setSize(300, 150),
                    Size2.create(100, 150));
                setClipTest.run(
                    Size2.create(100, 200),
                    UIPainterSetClipSizeOptions.create()
                        .setSize(75, 250),
                    Size2.create(75, 200));
                setClipTest.run(
                    Size2.create(100, 200),
                    UIPainterSetClipSizeOptions.create()
                        .setSize(75, 100),
                    Size2.create(75, 100));
                setClipTest.run(
                    Size2.create(100, 200),
                    UIPainterSetClipSizeOptions.create()
                        .setSize(300, 250)
                        .setResetClip(true),
                    Size2.create(300, 250));
            });
        });
    }
}
