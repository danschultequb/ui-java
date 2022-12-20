package qub;

public interface MutableColorTests
{
    public static void test(TestRunner runner)
    {
        runner.testGroup(MutableColor.class, () ->
        {
            runner.testGroup("create(int,int,int)", () ->
            {
                final Action4<Integer,Integer,Integer,Throwable> createErrorTest = (Integer red, Integer green, Integer blue, Throwable expected) ->
                {
                    runner.test("with " + English.andList(red, green, blue), (Test test) ->
                    {
                        test.assertThrows(() -> MutableColor.create(red, green, blue), expected);
                    });
                };

                createErrorTest.run(-1, 0, 0, new PreConditionFailure("red (-1) must be between 0 and 255."));
                createErrorTest.run(256, 0, 0, new PreConditionFailure("red (256) must be between 0 and 255."));
                createErrorTest.run(0, -1, 0, new PreConditionFailure("green (-1) must be between 0 and 255."));
                createErrorTest.run(0, 256, 0, new PreConditionFailure("green (256) must be between 0 and 255."));
                createErrorTest.run(0, 0, -1, new PreConditionFailure("blue (-1) must be between 0 and 255."));
                createErrorTest.run(0, 0, 256, new PreConditionFailure("blue (256) must be between 0 and 255."));

                final Action3<Integer,Integer,Integer> createTest = (Integer red, Integer green, Integer blue) ->
                {
                    runner.test("with " + English.andList(red, green, blue), (Test test) ->
                    {
                        final MutableColor color = MutableColor.create(red, green, blue);
                        test.assertNotNull(color);
                        test.assertEqual(red, color.getRed());
                        test.assertEqual(green, color.getGreen());
                        test.assertEqual(blue, color.getBlue());
                        test.assertEqual(255, color.getAlpha());
                    });
                };

                createTest.run(1, 2, 3);
                createTest.run(255, 0, 128);
            });

            runner.testGroup("create(int,int,int,int)", () ->
            {
                final Action5<Integer,Integer,Integer,Integer,Throwable> createErrorTest = (Integer red, Integer green, Integer blue, Integer alpha, Throwable expectedError) ->
                {
                    runner.test("with " + red + ", " + green + ", " + blue + ", and " + alpha, (Test test) ->
                    {
                        test.assertThrows(() -> MutableColor.create(red, green, blue, alpha), expectedError);
                    });
                };

                createErrorTest.run(-1, 0, 0, 0, new PreConditionFailure("red (-1) must be between 0 and 255."));
                createErrorTest.run(256, 0, 0, 0, new PreConditionFailure("red (256) must be between 0 and 255."));
                createErrorTest.run(0, -1, 0, 0, new PreConditionFailure("green (-1) must be between 0 and 255."));
                createErrorTest.run(0, 256, 0, 0, new PreConditionFailure("green (256) must be between 0 and 255."));
                createErrorTest.run(0, 0, -1, 0, new PreConditionFailure("blue (-1) must be between 0 and 255."));
                createErrorTest.run(0, 0, 256, 0, new PreConditionFailure("blue (256) must be between 0 and 255."));
                createErrorTest.run(0, 0, 0, -1, new PreConditionFailure("alpha (-1) must be between 0 and 255."));
                createErrorTest.run(0, 0, 0, 256, new PreConditionFailure("alpha (256) must be between 0 and 255."));

                final Action4<Integer,Integer,Integer,Integer> createTest = (Integer red, Integer green, Integer blue, Integer alpha) ->
                {
                    runner.test("with " + English.andList(red, green, blue, alpha), (Test test) ->
                    {
                        final MutableColor color = MutableColor.create(red, green, blue, alpha);
                        test.assertNotNull(color);
                        test.assertEqual(red, color.getRed());
                        test.assertEqual(green, color.getGreen());
                        test.assertEqual(blue, color.getBlue());
                        test.assertEqual(alpha, color.getAlpha());
                    });
                };

                createTest.run(1, 2, 3, 4);
                createTest.run(255, 0, 128, 184);
            });
        });
    }
}
