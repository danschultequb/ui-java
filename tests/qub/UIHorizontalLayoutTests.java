package qub;

public interface UIHorizontalLayoutTests
{
    public static void test(TestRunner runner, Function0<? extends UIHorizontalLayout> creator)
    {
        PreCondition.assertNotNull(runner, "runner");
        PreCondition.assertNotNull(creator, "creator");

        runner.testGroup(UIHorizontalLayout.class, () ->
        {
            runner.testGroup("setBackgroundColor(Color)", () ->
            {
                runner.test("check return value type", (Test test) ->
                {
                    final UIHorizontalLayout uiElement = creator.run();

                    final UIHorizontalLayout setBackgroundColorResult = uiElement.setBackgroundColor(Color.red);
                    test.assertSame(uiElement, setBackgroundColorResult);
                    test.assertEqual(Color.red, uiElement.getBackgroundColor());
                });
            });

            runner.testGroup("add(UIElement)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final UIHorizontalLayout verticalLayout = creator.run();
                    test.assertThrows(() -> verticalLayout.add((UIElement)null),
                        new PreConditionFailure("uiElement cannot be null."));
                });

                runner.test("with non-null", (Test test) ->
                {
                    final UIHorizontalLayout verticalLayout = creator.run();
                    final UIElement element = creator.run();

                    final UIHorizontalLayout addResult = verticalLayout.add(element);
                    test.assertSame(verticalLayout, addResult);
                });
            });

            runner.testGroup("addAll(UIElement...)", () ->
            {
                runner.test("with null element", (Test test) ->
                {
                    final UIHorizontalLayout verticalLayout = creator.run();
                    test.assertThrows(() -> verticalLayout.addAll((UIElement)null),
                        new PreConditionFailure("uiElement cannot be null."));
                });

                runner.test("with no arguments", (Test test) ->
                {
                    final UIHorizontalLayout verticalLayout = creator.run();
                    final UIHorizontalLayout addResult = verticalLayout.addAll();
                    test.assertSame(verticalLayout, addResult);
                });

                runner.test("with one argument", (Test test) ->
                {
                    final UIHorizontalLayout verticalLayout = creator.run();
                    final UIElement element = creator.run();

                    final UIHorizontalLayout addResult = verticalLayout.addAll(element);
                    test.assertSame(verticalLayout, addResult);
                });

                runner.test("with multiple arguments", (Test test) ->
                {
                    final UIHorizontalLayout verticalLayout = creator.run();
                    final UIElement element1 = creator.run();
                    final UIElement element2 = creator.run();

                    final UIHorizontalLayout addResult = verticalLayout.addAll(element1, element2);
                    test.assertSame(verticalLayout, addResult);
                });

                runner.test("with null array", (Test test) ->
                {
                    final UIHorizontalLayout verticalLayout = creator.run();
                    test.assertThrows(() -> verticalLayout.addAll((UIElement[])null),
                        new PreConditionFailure("uiElements cannot be null."));
                });

                runner.test("with empty array", (Test test) ->
                {
                    final UIHorizontalLayout verticalLayout = creator.run();
                    final UIHorizontalLayout addResult = verticalLayout.addAll(new UIElement[0]);
                    test.assertSame(verticalLayout, addResult);
                });

                runner.test("with one element array", (Test test) ->
                {
                    final UIHorizontalLayout verticalLayout = creator.run();
                    final UIElement element = creator.run();

                    final UIHorizontalLayout addResult = verticalLayout.addAll(new UIElement[]{ element });
                    test.assertSame(verticalLayout, addResult);
                });

                runner.test("with multiple element array", (Test test) ->
                {
                    final UIHorizontalLayout verticalLayout = creator.run();
                    final UIElement element1 = creator.run();
                    final UIElement element2 = creator.run();

                    final UIHorizontalLayout addResult = verticalLayout.addAll(new UIElement[]{ element1, element2 });
                    test.assertSame(verticalLayout, addResult);
                });
            });

            runner.testGroup("addAll(Iterable<UIElement>)", () ->
            {
                runner.test("with null Iterable", (Test test) ->
                {
                    final UIHorizontalLayout verticalLayout = creator.run();
                    test.assertThrows(() -> verticalLayout.addAll((Iterable<UIElement>)null),
                        new PreConditionFailure("uiElements cannot be null."));
                });

                runner.test("with empty Iterable", (Test test) ->
                {
                    final UIHorizontalLayout verticalLayout = creator.run();
                    final UIHorizontalLayout addResult = verticalLayout.addAll(Iterable.create());
                    test.assertSame(verticalLayout, addResult);
                });

                runner.test("with one element Iterable", (Test test) ->
                {
                    final UIHorizontalLayout verticalLayout = creator.run();
                    final UIElement element = creator.run();

                    final UIHorizontalLayout addResult = verticalLayout.addAll(Iterable.create(element));
                    test.assertSame(verticalLayout, addResult);
                });

                runner.test("with multiple element Iterable", (Test test) ->
                {
                    final UIHorizontalLayout verticalLayout = creator.run();
                    final UIElement element1 = creator.run();
                    final UIElement element2 = creator.run();

                    final UIHorizontalLayout addResult = verticalLayout.addAll(Iterable.create(element1, element2));
                    test.assertSame(verticalLayout, addResult);
                });
            });
        });
    }
}
