package qub;

public interface MutableDynamicSize2IntegerTests
{
    public static void test(TestRunner runner)
    {
        runner.testGroup(MutableDynamicSize2Integer.class, () ->
        {
            runner.test("create()", (Test test) ->
            {
                final MutableDynamicSize2Integer dynamicSize = MutableDynamicSize2Integer.create();
                test.assertNotNull(dynamicSize);
            });

            runner.testGroup("create(int,int)", () ->
            {
                final Action3<Integer,Integer,Throwable> createErrorTest = (Integer width, Integer height, Throwable expected) ->
                {
                    runner.test("with " + English.andList(width, height), (Test test) ->
                    {
                        test.assertThrows(() -> MutableDynamicSize2Integer.create(width, height),
                            expected);
                    });
                };

                createErrorTest.run(-1, 0, new PreConditionFailure("width (-1) must be greater than or equal to 0."));
                createErrorTest.run(0, -1, new PreConditionFailure("height (-1) must be greater than or equal to 0."));

                final Action2<Integer,Integer> createTest = (Integer width, Integer height) ->
                {
                    runner.test("with " + English.andList(width, height), (Test test) ->
                    {
                        final MutableDynamicSize2Integer dynamicSize = DynamicSize2Integer.create(width, height);
                        test.assertNotNull(dynamicSize);
                        test.assertEqual(width, dynamicSize.getWidth());
                        test.assertEqual(height, dynamicSize.getHeight());
                    });
                };

                createTest.run(0, 0);
                createTest.run(1, 0);
                createTest.run(0, 1);
                createTest.run(1, 2);
            });

            runner.testGroup("create(Size2<Integer>)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    test.assertThrows(() -> MutableDynamicSize2Integer.create(null),
                        new PreConditionFailure("size cannot be null."));
                });

                final Action1<Size2<Integer>> createTest = (Size2<Integer> size) ->
                {
                    runner.test("with " + size, (Test test) ->
                    {
                        final MutableDynamicSize2Integer dynamicSize = MutableDynamicSize2Integer.create(size);
                        test.assertNotNull(dynamicSize);
                        test.assertEqual(size, dynamicSize);
                    });
                };

                createTest.run(Size2.create(0, 0));
                createTest.run(Size2.create(1, 0));
                createTest.run(Size2.create(0, 1));
                createTest.run(Size2.create(1, 2));
            });
        });
    }

    public static void test(TestRunner runner, Function0<? extends MutableDynamicSize2Integer> creator)
    {
        runner.testGroup(MutableDynamicSize2Integer.class, () ->
        {
            DynamicSize2IntegerTests.test(runner, creator);

            runner.testGroup("onChanged(Action0)", () ->
            {
                runner.test("with non-null", (Test test) ->
                {
                    final MutableDynamicSize2Integer dynamicSize = creator.run();

                    final List<Size2Integer> list = List.create();
                    try (final Disposable subscription = dynamicSize.onChanged(() ->
                    {
                        list.add(Size2.create(dynamicSize.getWidth(), dynamicSize.getHeight()));
                    }))
                    {
                        test.assertEqual(Iterable.create(), list);

                        final Size2Integer size1 = Size2.create(dynamicSize.getWidth() + 1, dynamicSize.getHeight() + 1);
                        dynamicSize.set(size1);
                        test.assertEqual(Iterable.create(size1), list);

                        final Size2Integer size2 = Size2.create(dynamicSize.getWidth() + 1, dynamicSize.getHeight() + 1);
                        dynamicSize.set(size2);
                        test.assertEqual(Iterable.create(size1, size2), list);

                        subscription.dispose().await();

                        final Size2Integer size3 = Size2.create(dynamicSize.getWidth() + 1, dynamicSize.getHeight() + 1);
                        dynamicSize.set(size3);
                        test.assertEqual(Iterable.create(size1, size2), list);
                    }
                });
            });

            runner.testGroup("onChanged(Action1<SizeChange>)", () ->
            {
                runner.test("with non-null", (Test test) ->
                {
                    final MutableDynamicSize2Integer dynamicSize = creator.run();
                    final Size2Integer size1 = Size2.create(dynamicSize.getWidth(), dynamicSize.getHeight());

                    final List<SizeChange> list = List.create();
                    try (final Disposable subscription = dynamicSize.onChanged(list::add))
                    {
                        test.assertEqual(Iterable.create(), list);

                        final Size2Integer size2 = Size2.create(dynamicSize.getWidth() + 1, dynamicSize.getHeight() + 1);
                        dynamicSize.set(size2);
                        test.assertEqual(
                            Iterable.create(
                                SizeChange.create()
                                    .setPreviousSize(size1)
                                    .setNewSize(size2)),
                            list);

                        final Size2Integer size3 = Size2.create(dynamicSize.getWidth() + 1, dynamicSize.getHeight() + 1);
                        dynamicSize.set(size3);
                        test.assertEqual(
                            Iterable.create(
                                SizeChange.create()
                                    .setPreviousSize(size1)
                                    .setNewSize(size2),
                                SizeChange.create()
                                    .setPreviousSize(size2)
                                    .setNewSize(size3)),
                            list);

                        subscription.dispose().await();

                        final Size2Integer size4 = Size2.create(dynamicSize.getWidth() + 1, dynamicSize.getHeight() + 1);
                        dynamicSize.set(size4);
                        test.assertEqual(
                            Iterable.create(
                                SizeChange.create()
                                    .setPreviousSize(size1)
                                    .setNewSize(size2),
                                SizeChange.create()
                                    .setPreviousSize(size2)
                                    .setNewSize(size3)),
                            list);
                    }
                });
            });
        });
    }
}
