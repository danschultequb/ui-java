package qub;

public interface ColorTests
{
    public static void test(TestRunner runner)
    {
        runner.testGroup(Color.class, () ->
        {
            runner.testGroup("create(int,int,int)", () ->
            {
                final Action4<Integer,Integer,Integer,Throwable> createErrorTest = (Integer red, Integer green, Integer blue, Throwable expectedError) ->
                {
                    runner.test("with " + English.andList(red, green, blue), (Test test) ->
                    {
                        test.assertThrows(() -> Color.create(red, green, blue), expectedError);
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
                        final Color color = Color.create(red, green, blue);
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
                    runner.test("with " + English.andList(red, green, blue, alpha), (Test test) ->
                    {
                        test.assertThrows(() -> Color.create(red, green, blue, alpha), expectedError);
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
                        final Color color = Color.create(red, green, blue, alpha);
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

            runner.testGroup("toString()", () ->
            {
                final Action2<Color,String> toStringTest = (Color color, String expected) ->
                {
                    runner.test("with " + color, (Test test) ->
                    {
                        test.assertEqual(expected, color.toString());
                    });
                };

                toStringTest.run(Color.create(0, 1, 2), "{\"red\":0,\"green\":1,\"blue\":2,\"alpha\":255}");
                toStringTest.run(Color.create(40, 50, 60, 70), "{\"red\":40,\"green\":50,\"blue\":60,\"alpha\":70}");
            });

            runner.testGroup("equals(Object)", () ->
            {
                final Action3<Color,Object,Boolean> equalsTest = (Color lhs, Object rhs, Boolean expected) ->
                {
                    runner.test("with " + English.andList(lhs, rhs), (Test test) ->
                    {
                        test.assertEqual(expected, lhs.equals(rhs));
                    });
                };

                equalsTest.run(Color.red, null, false);
                equalsTest.run(Color.red, "test", false);
                equalsTest.run(Color.red, Color.red, true);
                equalsTest.run(Color.red, Color.create(255, 0, 0), true);
                equalsTest.run(Color.red, Color.white, false);
                equalsTest.run(Color.transparent, Color.create(1, 2, 3, 0), true);
                equalsTest.run(Color.create(1, 2, 3, 4), Color.create(1, 2, 3, 5), false);
                equalsTest.run(Color.create(1, 2, 3, 5), Color.create(10, 2, 3, 5), false);
                equalsTest.run(Color.create(1, 2, 3, 5), Color.create(1, 20, 3, 5), false);
                equalsTest.run(Color.create(1, 2, 3, 5), Color.create(1, 2, 30, 5), false);
                equalsTest.run(Color.create(1, 2, 3, 5), Color.create(1, 2, 3, 5), true);
            });

            runner.testGroup("equals(Color)", () ->
            {
                final Action3<Color,Color,Boolean> equalsTest = (Color lhs, Color rhs, Boolean expected) ->
                {
                    runner.test("with " + English.andList(lhs, rhs), (Test test) ->
                    {
                        test.assertEqual(expected, lhs.equals(rhs));
                    });
                };

                equalsTest.run(Color.red, null, false);
                equalsTest.run(Color.red, Color.red, true);
                equalsTest.run(Color.red, Color.create(255, 0, 0), true);
                equalsTest.run(Color.red, Color.white, false);
                equalsTest.run(Color.transparent, Color.create(1, 2, 3, 0), true);
                equalsTest.run(Color.create(1, 2, 3, 4), Color.create(1, 2, 3, 5), false);
                equalsTest.run(Color.create(1, 2, 3, 5), Color.create(10, 2, 3, 5), false);
                equalsTest.run(Color.create(1, 2, 3, 5), Color.create(1, 20, 3, 5), false);
                equalsTest.run(Color.create(1, 2, 3, 5), Color.create(1, 2, 30, 5), false);
                equalsTest.run(Color.create(1, 2, 3, 5), Color.create(1, 2, 3, 5), true);
            });

            runner.testGroup("hashCode()", () ->
            {
                runner.test("with same", (Test test) ->
                {
                    test.assertEqual(Color.red.hashCode(), Color.red.hashCode());
                });

                runner.test("with equal", (Test test) ->
                {
                    test.assertEqual(Color.create(1, 2, 3).hashCode(), Color.create(1, 2, 3).hashCode());
                });

                runner.test("with different", (Test test) ->
                {
                    test.assertNotEqual(Color.red.hashCode(), Color.blue.hashCode());
                });
            });

            runner.test("white", (Test test) ->
            {
                final Color white = Color.white;
                test.assertNotNull(white);
                test.assertEqual(255, white.getRed());
                test.assertEqual(255, white.getGreen());
                test.assertEqual(255, white.getBlue());
                test.assertEqual(255, white.getAlpha());
            });

            runner.test("black", (Test test) ->
            {
                final Color black = Color.black;
                test.assertNotNull(black);
                test.assertEqual(0, black.getRed());
                test.assertEqual(0, black.getGreen());
                test.assertEqual(0, black.getBlue());
                test.assertEqual(255, black.getAlpha());
            });

            runner.test("red", (Test test) ->
            {
                final Color red = Color.red;
                test.assertNotNull(red);
                test.assertEqual(255, red.getRed());
                test.assertEqual(0, red.getGreen());
                test.assertEqual(0, red.getBlue());
                test.assertEqual(255, red.getAlpha());
            });

            runner.test("green", (Test test) ->
            {
                final Color green = Color.green;
                test.assertNotNull(green);
                test.assertEqual(0, green.getRed());
                test.assertEqual(255, green.getGreen());
                test.assertEqual(0, green.getBlue());
                test.assertEqual(255, green.getAlpha());
            });

            runner.test("blue", (Test test) ->
            {
                final Color blue = Color.blue;
                test.assertNotNull(blue);
                test.assertEqual(0, blue.getRed());
                test.assertEqual(0, blue.getGreen());
                test.assertEqual(255, blue.getBlue());
                test.assertEqual(255, blue.getAlpha());
            });
        });
    }
}
