package qub;

public interface JavaBufferedImageTests
{
    public static void test(TestRunner runner)
    {
        runner.testGroup(JavaBufferedImage.class, () ->
        {
            runner.testGroup("create(int,int)", () ->
            {
                final Action3<Integer,Integer,Throwable> createErrorTest = (Integer width, Integer height, Throwable expected) ->
                {
                    runner.test("with " + English.andList(width, height), (Test test) ->
                    {
                        test.assertThrows(() -> JavaBufferedImage.create(width, height),
                            expected);
                    });
                };

                createErrorTest.run(-1, -1, new PreConditionFailure("width (-1) must be greater than or equal to 1."));
                createErrorTest.run(-1, 1, new PreConditionFailure("width (-1) must be greater than or equal to 1."));
                createErrorTest.run(0, 1, new PreConditionFailure("width (0) must be greater than or equal to 1."));
                createErrorTest.run(1, -1, new PreConditionFailure("height (-1) must be greater than or equal to 1."));
                createErrorTest.run(1, 0, new PreConditionFailure("height (0) must be greater than or equal to 1."));

                final Action3<Integer,Integer,int[]> createTest = (Integer width, Integer height, int[] expectedPixels) ->
                {
                    runner.test("with " + English.andList(width, height), (Test test) ->
                    {
                        final JavaBufferedImage image = JavaBufferedImage.create(width, height);
                        test.assertEqual(width, image.getWidth());
                        test.assertEqual(height, image.getHeight());
                        test.assertEqual(java.awt.image.BufferedImage.TYPE_INT_ARGB, image.getType());
                        test.assertEqual(expectedPixels, image.getPixels());

                        final java.awt.Graphics2D graphics = image.getGraphics();
                        test.assertNotNull(graphics);
                    });
                };

                createTest.run(1, 1, new int[]
                {
                    0x00000000
                });
                createTest.run(1, 3, new int[]
                {
                    0x00000000,
                    0x00000000,
                    0x00000000
                });
                createTest.run(5, 5, new int[]
                {
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
                    0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000
                });
            });

            runner.testGroup("setPixels(int...)", () ->
            {
                runner.test("with null rgb", (Test test) ->
                {
                    final JavaBufferedImage image = JavaBufferedImage.create(1, 1);
                    test.assertThrows(() -> image.setPixels(null),
                        new PreConditionFailure("rgb cannot be null."));
                    test.assertEqual(
                        new int[]
                        {
                            0
                        },
                        image.getPixels());
                });

                runner.test("with no arguments", (Test test) ->
                {
                    final JavaBufferedImage image = JavaBufferedImage.create(1, 1);
                    test.assertThrows(() -> image.setPixels(),
                        new PreConditionFailure("rgb.length (0) must be 1."));
                    test.assertEqual(
                        new int[]
                        {
                            0
                        },
                        image.getPixels());
                });

                runner.test("with rgb with too few arguments", (Test test) ->
                {
                    final JavaBufferedImage image = JavaBufferedImage.create(2, 2);
                    test.assertThrows(() -> image.setPixels(0, 1, 2),
                        new PreConditionFailure("rgb.length (3) must be 4."));
                    test.assertEqual(
                        new int[]
                        {
                            0, 0,
                            0, 0
                        },
                        image.getPixels());
                });

                runner.test("with rgb with too many arguments", (Test test) ->
                {
                    final JavaBufferedImage image = JavaBufferedImage.create(1, 1);
                    test.assertThrows(() -> image.setPixels(0, 1),
                        new PreConditionFailure("rgb.length (2) must be 1."));
                    test.assertEqual(
                        new int[]
                        {
                            0
                        },
                        image.getPixels());
                });

                runner.test("with correct argument count", (Test test) ->
                {
                    final JavaBufferedImage image = JavaBufferedImage.create(2, 2);
                    final JavaBufferedImage setPixelsResult = image.setPixels(1, 2, 3, 4);
                    test.assertSame(image, setPixelsResult);
                    test.assertEqual(
                        new int[]
                        {
                            1, 2,
                            3, 4
                        },
                        image.getPixels());
                });
            });
        });
    }
}
