#! /bin/zsh

rm *.log*

run_with_this_gc () {
    echo -e "\n\n\e[1mUsing $1\e[0m\n\n"
    mvn clean compile exec:exec -DmyGCType="$1" -Ddefined="$2"
}

get_gs_type() {
    echo "Choose GC-type:"
    echo "[1] - G1 GC, until OOM, with gc-logs output"
    echo "[2] - Serial GC, until OOM, with gc-logs output"
    echo "[3] - Parallel GC, until OOM, with gc-logs output"
    echo "[4] - CMS GC, until OOM, with gc-logs output"
    echo "[5] - Run with each type of GC for 2 minutes and calc statistics"
    echo -n "Your choice is: "
    read gc_type_id

    case $gc_type_id in
        1)
            run_with_this_gc G1GC
            ;;
        2)
            run_with_this_gc SerialGC
            ;;
        3)
            run_with_this_gc ParallelGC
            ;;
        4)
            run_with_this_gc ConcMarkSweepGC
            ;;
        5)
            echo -e "\n\n\e[1mRun test with all GS\e[0m\n\n"
            run_with_this_gc G1GC defined
            run_with_this_gc SerialGC defined
            run_with_this_gc ParallelGC defined
            run_with_this_gc ConcMarkSweepGC defined
            ;;
        *)
            get_gs_type
            ;;
    esac
}

get_gs_type
