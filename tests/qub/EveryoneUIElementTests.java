package qub;

public interface EveryoneUIElementTests
{
    public static void test(TestRunner runner, Function0<? extends EveryoneUIElement> creator)
    {
        runner.testGroup(EveryoneUIElement.class,
            (TestResources resources) -> Tuple.create(resources.getMainAsyncRunner()),
            (AsyncScheduler mainAsyncRunner) ->
        {
            UIElementTests.test(runner, creator);

            runner.testGroup("setParent(EveryoneUIElementParent)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final EveryoneUIElement uiElement = creator.run();
                    final EveryoneUIElement setParentResult = uiElement.setParent(null);
                    test.assertSame(uiElement, setParentResult);
                    test.assertNull(uiElement.getParent());
                });

                runner.test("with non-null", (Test test) ->
                {
                    final EveryoneUIElement uiElement = creator.run();
                    final FakeEveryoneUIElementParent parent = FakeEveryoneUIElementParent.create();
                    final EveryoneUIElement setParentResult = uiElement.setParent(parent);
                    test.assertSame(uiElement, setParentResult);
                    test.assertSame(parent, uiElement.getParent());
                });
            });

            runner.testGroup("repaint()", () ->
            {
                runner.test("with no parent", (Test test) ->
                {
                    final EveryoneUIElement uiElement = creator.run()
                        .setParent(null);
                    uiElement.repaint();
                });

                runner.test("with parent", (Test test) ->
                {
                    final IntegerValue counter = IntegerValue.create(0);
                    final FakeEveryoneUIElementParent parent = FakeEveryoneUIElementParent.create()
                        .setRepaintAction(counter::increment);
                    final EveryoneUIElement uiElement = creator.run()
                        .setParent(parent);

                    uiElement.repaint();

                    test.assertEqual(1, counter.get());
                });
            });

            runner.testGroup("setBackgroundColor(Color)", () ->
            {
                runner.test("when value changes", (Test test) ->
                {
                    final EveryoneUIElement uiButton = creator.run()
                        .setBackgroundColor(Color.green);

                    final BooleanValue triggered = BooleanValue.create();
                    final FakeEveryoneUIElementParent parent = FakeEveryoneUIElementParent.create()
                        .setRepaintAction(() -> triggered.set(true));
                    uiButton.setParent(parent);

                    final EveryoneUIElement setBackgroundColorResult = uiButton.setBackgroundColor(Color.blue);
                    test.assertSame(uiButton, setBackgroundColorResult);
                    test.assertEqual(Color.blue, uiButton.getBackgroundColor());
                    test.assertTrue(triggered.get());
                });

                runner.test("when value doesn't change", (Test test) ->
                {
                    final EveryoneUIElement uiButton = creator.run()
                        .setBackgroundColor(Color.green);

                    final BooleanValue triggered = BooleanValue.create();
                    final FakeEveryoneUIElementParent parent = FakeEveryoneUIElementParent.create()
                        .setRepaintAction(() -> triggered.set(true));
                    uiButton.setParent(parent);

                    final EveryoneUIElement setBackgroundColorResult = uiButton.setBackgroundColor(Color.green);
                    test.assertSame(uiButton, setBackgroundColorResult);
                    test.assertEqual(Color.green, uiButton.getBackgroundColor());
                    test.assertFalse(triggered.hasValue());
                });

                runner.testGroup("with transparent", () ->
                {
                    final Action1<Color> setBackgroundColorTest = (Color color) ->
                    {
                        runner.test("with " + color, (Test test) ->
                        {
                            final EveryoneUIElement uiElement = creator.run();
                            final EveryoneUIElement setBackgroundColorResult = uiElement.setBackgroundColor(color);
                            test.assertSame(uiElement, setBackgroundColorResult);
                            test.assertEqual(color, uiElement.getBackgroundColor());
                        });
                    };

                    setBackgroundColorTest.run(Color.create(1, 2, 3, Color.ComponentMin));
                    setBackgroundColorTest.run(Color.create(5, 6, 7, 8));
                });
            });

            runner.testGroup("setWidth(int)", () ->
            {
                runner.test("check return value type", (Test test) ->
                {
                    final EveryoneUIElement uiElement = creator.run();
                    final EveryoneUIElement setWidthResult = uiElement.setWidth(100);
                    test.assertSame(uiElement, setWidthResult);
                    test.assertEqual(100, uiElement.getWidth());
                });
            });

            runner.testGroup("setWidth(Distance)", () ->
            {
                runner.test("check return value type", (Test test) ->
                {
                    final EveryoneUIElement uiElement = creator.run();
                    final EveryoneUIElement setWidthResult = uiElement.setWidth(Distance.inches(1));
                    test.assertSame(uiElement, setWidthResult);
                    test.assertEqual(100, uiElement.getWidth());
                });
            });

            runner.testGroup("setHeight(int)", () ->
            {
                runner.test("check return value type", (Test test) ->
                {
                    final EveryoneUIElement uiElement = creator.run();
                    final EveryoneUIElement setHeightResult = uiElement.setHeight(200);
                    test.assertSame(uiElement, setHeightResult);
                    test.assertEqual(200, uiElement.getHeight());
                });
            });

            runner.testGroup("setHeight(Distance)", () ->
            {
                runner.test("check return value type", (Test test) ->
                {
                    final EveryoneUIElement uiElement = creator.run();
                    final EveryoneUIElement setHeightResult = uiElement.setHeight(Distance.inches(1));
                    test.assertSame(uiElement, setHeightResult);
                    test.assertEqual(200, uiElement.getHeight());
                });
            });

            runner.testGroup("setSize(int,int)", () ->
            {
                runner.test("check return value type", (Test test) ->
                {
                    final EveryoneUIElement uiElement = creator.run();
                    final EveryoneUIElement setSizeResult = uiElement.setSize(100, 200);
                    test.assertSame(uiElement, setSizeResult);
                    test.assertEqual(100, uiElement.getWidth());
                    test.assertEqual(200, uiElement.getHeight());
                });
            });

            runner.testGroup("setSize(Size2Integer)", () ->
            {
                runner.test("check return value type", (Test test) ->
                {
                    final EveryoneUIElement uiElement = creator.run();
                    final EveryoneUIElement setSizeResult = uiElement.setSize(Size2.create(100, 200));
                    test.assertSame(uiElement, setSizeResult);
                    test.assertEqual(Size2.create(100, 200), uiElement.getSize());
                });
            });

            runner.testGroup("setSize(Distance,Distance)", () ->
            {
                runner.test("check return value type", (Test test) ->
                {
                    final EveryoneUIElement uiElement = creator.run();
                    final EveryoneUIElement setSizeResult = uiElement.setSize(Distance.inches(1), Distance.inches(10));
                    test.assertSame(uiElement, setSizeResult);
                    test.assertEqual(100, uiElement.getWidth());
                    test.assertEqual(2000, uiElement.getHeight());
                });
            });

            runner.testGroup("setSize(Size2Distance)", () ->
            {
                runner.test("check return value type", (Test test) ->
                {
                    final EveryoneUIElement uiElement = creator.run();
                    final EveryoneUIElement setSizeResult = uiElement.setSize(Size2.create(Distance.inches(1), Distance.inches(10)));
                    test.assertSame(uiElement, setSizeResult);
                    test.assertEqual(Size2.create(100, 2000), uiElement.getSize());
                });
            });

            runner.testGroup("setDynamicSize(DynamicSize2Integer)", () ->
            {
                runner.test("check return value type", (Test test) ->
                {
                    final EveryoneUIElement uiElement = creator.run();
                    final MutableDynamicSize2Integer dynamicSize = DynamicSize2Integer.create(100, 200);
                    final EveryoneUIElement setSizeResult = uiElement.setDynamicSize(dynamicSize);
                    test.assertSame(uiElement, setSizeResult);
                    test.assertEqual(Size2.create(100, 200), uiElement.getSize());
                });
            });

            runner.testGroup("paint(UIPainter)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final EveryoneUIElement uiElement = creator.run();
                    test.assertThrows(() -> uiElement.paint(null),
                        new PreConditionFailure("painter cannot be null."));
                });

                runner.test("with transparent background", (Test test) ->
                {
                    final FakeUIPainter painter = FakeUIPainter.create();
                    final EveryoneUIElement uiElement = creator.run()
                        .setBackgroundColor(Color.transparent);

                    uiElement.paint(painter);

                    test.assertFalse(painter.getDrawOperations()
                        .contains(FakeDrawRectangleOperation.create()
                            .setOptions(DrawRectangleOptions.create()
                                .setLeftX(0)
                                .setTopY(0)
                                .setWidth(uiElement.getWidth())
                                .setHeight(uiElement.getHeight())
                                .setLineColor(Color.transparent)
                                .setFillColor(Color.transparent))));
                });

                runner.test("with non-transparent background", (Test test) ->
                {
                    final FakeUIPainter painter = FakeUIPainter.create();
                    final EveryoneUIElement uiElement = creator.run()
                        .setBackgroundColor(Color.green);

                    uiElement.paint(painter);

                    test.assertTrue(painter.getDrawOperations()
                        .contains(FakeDrawRectangleOperation.create()
                            .setOptions(DrawRectangleOptions.create()
                                .setLeftX(0)
                                .setTopY(0)
                                .setWidth(uiElement.getWidth())
                                .setHeight(uiElement.getHeight())
                                .setLineColor(Color.transparent)
                                .setFillColor(Color.green))));
                });
            });
        });
    }
}
