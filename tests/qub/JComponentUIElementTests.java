package qub;

public interface JComponentUIElementTests
{
    public static void test(TestRunner runner, Function0<? extends JComponentUIElement> creator)
    {
        runner.testGroup(JComponentUIElement.class, () ->
        {
            UIElementTests.test(runner, creator);

            runner.testGroup("setBackgroundColor(Color)", () ->
            {
                runner.test("check return value type", (Test test) ->
                {
                    final JComponentUIElement uiElement = creator.run();

                    final JComponentUIElement setBackgroundColorResult = uiElement.setBackgroundColor(Color.red);
                    test.assertSame(uiElement, setBackgroundColorResult);
                    test.assertEqual(Color.red, uiElement.getBackgroundColor());
                });

                runner.testGroup("setBackgroundColor(Color)", () ->
                {
                    runner.testGroup("with transparent", () ->
                    {
                        final Action1<Color> setBackgroundColorTest = (Color color) ->
                        {
                            runner.test("with " + color, (Test test) ->
                            {
                                final JComponentUIElement uiElement = creator.run();
                                final JComponentUIElement setBackgroundColorResult = uiElement.setBackgroundColor(color);
                                test.assertSame(uiElement, setBackgroundColorResult);
                                test.assertEqual(color, uiElement.getBackgroundColor());
                            });
                        };

                        setBackgroundColorTest.run(Color.create(1, 2, 3, Color.ComponentMin));
                        setBackgroundColorTest.run(Color.create(5, 6, 7, 8));
                    });
                });
            });

            runner.testGroup("setWidth(int)", () ->
            {
                runner.test("check return value type", (Test test) ->
                {
                    final JComponentUIElement uiElement = creator.run();
                    final JComponentUIElement setWidthResult = uiElement.setWidth(100);
                    test.assertSame(uiElement, setWidthResult);
                    test.assertEqual(100, uiElement.getWidth());
                });
            });

            runner.testGroup("setWidth(Distance)", () ->
            {
                runner.test("check return value type", (Test test) ->
                {
                    final JComponentUIElement uiElement = creator.run();
                    final JComponentUIElement setWidthResult = uiElement.setWidth(Distance.inches(1));
                    test.assertSame(uiElement, setWidthResult);
                    test.assertEqual(100, uiElement.getWidth());
                });
            });

            runner.testGroup("setHeight(int)", () ->
            {
                runner.test("check return value type", (Test test) ->
                {
                    final JComponentUIElement uiElement = creator.run();
                    final JComponentUIElement setHeightResult = uiElement.setHeight(200);
                    test.assertSame(uiElement, setHeightResult);
                    test.assertEqual(200, uiElement.getHeight());
                });
            });

            runner.testGroup("setHeight(Distance)", () ->
            {
                runner.test("check return value type", (Test test) ->
                {
                    final JComponentUIElement uiElement = creator.run();
                    final JComponentUIElement setHeightResult = uiElement.setHeight(Distance.inches(1));
                    test.assertSame(uiElement, setHeightResult);
                    test.assertEqual(200, uiElement.getHeight());
                });
            });

            runner.testGroup("setSize(int,int)", () ->
            {
                runner.test("check return value type", (Test test) ->
                {
                    final JComponentUIElement uiElement = creator.run();
                    final JComponentUIElement setSizeResult = uiElement.setSize(100, 200);
                    test.assertSame(uiElement, setSizeResult);
                    test.assertEqual(100, uiElement.getWidth());
                    test.assertEqual(200, uiElement.getHeight());
                });
            });

            runner.testGroup("setSize(Size2Integer)", () ->
            {
                runner.test("check return value type", (Test test) ->
                {
                    final JComponentUIElement uiElement = creator.run();
                    final JComponentUIElement setSizeResult = uiElement.setSize(Size2.create(100, 200));
                    test.assertSame(uiElement, setSizeResult);
                    test.assertEqual(Size2.create(100, 200), uiElement.getSize());
                });
            });

            runner.testGroup("setDynamicSize(DynamicSize2Integer)", () ->
            {
                runner.test("check return value type", (Test test) ->
                {
                    final JComponentUIElement uiElement = creator.run();
                    final MutableDynamicSize2Integer dynamicSize = DynamicSize2Integer.create(100, 200);
                    final JComponentUIElement setSizeResult = uiElement.setDynamicSize(dynamicSize);
                    test.assertSame(uiElement, setSizeResult);
                    test.assertEqual(dynamicSize, uiElement.getSize());
                });
            });

            runner.test("getJComponent()", (Test test) ->
            {
                final JComponentUIElement jComponentUIElement = creator.run();
                test.assertNotNull(jComponentUIElement);

                final javax.swing.JComponent jComponent = jComponentUIElement.getJComponent();
                test.assertNotNull(jComponent);
                test.assertSame(jComponent, jComponentUIElement.getJComponent());
            });
        });
    }
}
