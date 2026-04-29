package com.mobil.modul3compose

object ProblemRepository {
    val problemList = listOf(
        CodeforcesProblem(
            problemId = "2220A",
            title = "Blocked",
            description = "Determine if the array elements can be rearranged so that no identical subsets can be formed in the prefix.",
            tags = listOf("greedy", "constructive algorithms"),
            url = "https://codeforces.com/contest/2220/problem/A",
            solutionCode = "Belum ada",
            img = R.drawable.a_blocked
        ),
        CodeforcesProblem(
            problemId = "2209B",
            title = "Array Operations",
            description = "Process the sequence to find the optimal arrangement based on the given continuous interval conditions.",
            tags = listOf("implementation", "math"),
            url = "https://codeforces.com/contest/2209/problem/B",
            solutionCode = "Belum ada",
            img = R.drawable.b_array_operation
        ),
        CodeforcesProblem(
            problemId = "2209A",
            title = "Initial Configuration",
            description = "Find the minimum number of operations required to achieve the valid array state.",
            tags = listOf("greedy", "sortings"),
            url = "https://codeforces.com/contest/2209/problem/A",
            solutionCode = "Belum ada",
            img = R.drawable.a_initial_config
        ),
        CodeforcesProblem(
            problemId = "2125B",
            title = "Left and Down",
            description = "Navigate a 2D coordinate system using only 'Left' and 'Down' moves to calculate the required combinations.",
            tags = listOf("dp", "geometry"),
            url = "https://codeforces.com/contest/2125/problem/B",
            solutionCode = "Belum ada",
            img = R.drawable.b_left_and_down
        ),
        CodeforcesProblem(
            problemId = "2209C",
            title = "Find the Zero",
            description = "Interactive problem: You are given a hidden array of length 2n containing 1 to n and n zeros. Query pairs to find the positions of the zeroes.",
            tags = listOf("interactive", "constructive algorithms"),
            url = "https://codeforces.com/contest/2209/problem/C",
            solutionCode = "Belum ada",
            img = R.drawable.c_find_the_zero
        )
    )

    fun getProblemById(id: String): CodeforcesProblem? {
        return problemList.find { it.problemId == id }
    }

    fun getAllProblems(): List<CodeforcesProblem> = problemList
}