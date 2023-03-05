package qub;

public interface SizeChangeTests
{
    public static void test(TestRunner runner)
    {
        runner.testGroup(SizeChange.class, () ->
        {
            runner.test("create()", (Test test) ->
            {
                final MutableSizeChange change = SizeChange.create();
                test.assertNotNull(change);
                test.assertEqual(0, change.getPreviousWidth());
                test.assertEqual(0, change.getPreviousHeight());
                test.assertEqual(Size2.create(0, 0), change.getPreviousSize());
                test.assertEqual(0, change.getNewWidth());
                test.assertEqual(0, change.getNewHeight());
                test.assertEqual(Size2.create(0, 0), change.getNewSize());
            });

            runner.testGroup("getWidthChanged()", () ->
            {
                final Action2<SizeChange,Boolean> getWidthChangedTest = (SizeChange change, Boolean expected) ->
                {
                    runner.test("with " + change, (Test test) ->
                    {
                        test.assertEqual(expected, change.getWidthChanged());
                    });
                };

                getWidthChangedTest.run(
                    SizeChange.create()
                        .setPreviousWidth(0)
                        .setNewWidth(0),
                    false);
                getWidthChangedTest.run(
                    SizeChange.create()
                        .setPreviousWidth(0)
                        .setNewWidth(1),
                    true);
            });

            runner.testGroup("getHeightChanged()", () ->
            {
                final Action2<SizeChange,Boolean> getHeightChangedTest = (SizeChange change, Boolean expected) ->
                {
                    runner.test("with " + change, (Test test) ->
                    {
                        test.assertEqual(expected, change.getHeightChanged());
                    });
                };

                getHeightChangedTest.run(
                    SizeChange.create()
                        .setPreviousHeight(0)
                        .setNewHeight(0),
                    false);
                getHeightChangedTest.run(
                    SizeChange.create()
                        .setPreviousHeight(0)
                        .setNewHeight(1),
                    true);
            });

            runner.testGroup("getPreviousSize()", () ->
            {
                final Action2<SizeChange,Size2Integer> getPreviousSizeTest = (SizeChange change, Size2Integer expected) ->
                {
                    runner.test("with " + change.toString(), (Test test) ->
                    {
                        test.assertEqual(expected, change.getPreviousSize());
                    });
                };

                getPreviousSizeTest.run(
                    SizeChange.create()
                        .setPreviousWidth(1)
                        .setPreviousHeight(2),
                    Size2.create(1, 2));
            });

            runner.testGroup("getNewSize()", () ->
            {
                final Action2<SizeChange,Size2Integer> getNewSizeTest = (SizeChange change, Size2Integer expected) ->
                {
                    runner.test("with " + change.toString(), (Test test) ->
                    {
                        test.assertEqual(expected, change.getNewSize());
                    });
                };

                getNewSizeTest.run(
                    SizeChange.create()
                        .setNewWidth(1)
                        .setNewHeight(2),
                    Size2.create(1, 2));
            });

            runner.testGroup("toJson()", () ->
            {
                final Action2<SizeChange,JSONObject> toJsonTest = (SizeChange change, JSONObject expected) ->
                {
                    runner.test("with " + change.toString(), (Test test) ->
                    {
                        test.assertEqual(expected, change.toJson());
                    });
                };

                toJsonTest.run(
                    SizeChange.create(),
                    JSONObject.create()
                        .setNumber("previousWidth", 0)
                        .setNumber("previousHeight", 0)
                        .setNumber("newWidth", 0)
                        .setNumber("newHeight", 0));
                toJsonTest.run(
                    SizeChange.create()
                        .setPreviousSize(1, 2)
                        .setNewSize(3, 4),
                    JSONObject.create()
                        .setNumber("previousWidth", 1)
                        .setNumber("previousHeight", 2)
                        .setNumber("newWidth", 3)
                        .setNumber("newHeight", 4));
            });

            runner.testGroup("toString()", () ->
            {
                final Action2<SizeChange,String> toJsonTest = (SizeChange change, String expected) ->
                {
                    runner.test("with " + change.toString(), (Test test) ->
                    {
                        test.assertEqual(expected, change.toString());
                    });
                };

                toJsonTest.run(
                    SizeChange.create(),
                    "{\"previousWidth\":0,\"previousHeight\":0,\"newWidth\":0,\"newHeight\":0}");
                toJsonTest.run(
                    SizeChange.create()
                        .setPreviousSize(1, 2)
                        .setNewSize(3, 4),
                    "{\"previousWidth\":1,\"previousHeight\":2,\"newWidth\":3,\"newHeight\":4}");
            });

            runner.testGroup("equals(Object)", () ->
            {
                final Action3<SizeChange,Object,Boolean> equalsTest = (SizeChange change, Object rhs, Boolean expected) ->
                {
                    runner.test("with " + English.andList(change, rhs), (Test test) ->
                    {
                        test.assertEqual(expected, change.equals(rhs));
                    });
                };

                equalsTest.run(
                    SizeChange.create(),
                    null,
                    false);
                equalsTest.run(
                    SizeChange.create(),
                    "hello",
                    false);
                equalsTest.run(
                    SizeChange.create(),
                    SizeChange.create(),
                    true);
                equalsTest.run(
                    SizeChange.create()
                        .setPreviousWidth(1),
                    SizeChange.create()
                        .setPreviousWidth(2),
                    false);
                equalsTest.run(
                    SizeChange.create()
                        .setPreviousWidth(1),
                    SizeChange.create()
                        .setPreviousWidth(1),
                    true);
                equalsTest.run(
                    SizeChange.create()
                        .setPreviousHeight(1),
                    SizeChange.create()
                        .setPreviousHeight(2),
                    false);
                equalsTest.run(
                    SizeChange.create()
                        .setPreviousHeight(1),
                    SizeChange.create()
                        .setPreviousHeight(1),
                    true);
                equalsTest.run(
                    SizeChange.create()
                        .setNewWidth(1),
                    SizeChange.create()
                        .setNewWidth(2),
                    false);
                equalsTest.run(
                    SizeChange.create()
                        .setNewWidth(1),
                    SizeChange.create()
                        .setNewWidth(1),
                    true);
                equalsTest.run(
                    SizeChange.create()
                        .setNewHeight(1),
                    SizeChange.create()
                        .setNewHeight(2),
                    false);
                equalsTest.run(
                    SizeChange.create()
                        .setNewHeight(1),
                    SizeChange.create()
                        .setNewHeight(1),
                    true);
            });

            runner.testGroup("equals(SizeChange)", () ->
            {
                final Action3<SizeChange,SizeChange,Boolean> equalsTest = (SizeChange change, SizeChange rhs, Boolean expected) ->
                {
                    runner.test("with " + English.andList(change, rhs), (Test test) ->
                    {
                        test.assertEqual(expected, change.equals(rhs));
                    });
                };

                equalsTest.run(
                    SizeChange.create(),
                    null,
                    false);
                equalsTest.run(
                    SizeChange.create(),
                    SizeChange.create(),
                    true);
                equalsTest.run(
                    SizeChange.create()
                        .setPreviousWidth(1),
                    SizeChange.create()
                        .setPreviousWidth(2),
                    false);
                equalsTest.run(
                    SizeChange.create()
                        .setPreviousWidth(1),
                    SizeChange.create()
                        .setPreviousWidth(1),
                    true);
                equalsTest.run(
                    SizeChange.create()
                        .setPreviousHeight(1),
                    SizeChange.create()
                        .setPreviousHeight(2),
                    false);
                equalsTest.run(
                    SizeChange.create()
                        .setPreviousHeight(1),
                    SizeChange.create()
                        .setPreviousHeight(1),
                    true);
                equalsTest.run(
                    SizeChange.create()
                        .setNewWidth(1),
                    SizeChange.create()
                        .setNewWidth(2),
                    false);
                equalsTest.run(
                    SizeChange.create()
                        .setNewWidth(1),
                    SizeChange.create()
                        .setNewWidth(1),
                    true);
                equalsTest.run(
                    SizeChange.create()
                        .setNewHeight(1),
                    SizeChange.create()
                        .setNewHeight(2),
                    false);
                equalsTest.run(
                    SizeChange.create()
                        .setNewHeight(1),
                    SizeChange.create()
                        .setNewHeight(1),
                    true);
            });
        });
    }
}
