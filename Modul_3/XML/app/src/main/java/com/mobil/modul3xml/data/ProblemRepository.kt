package com.mobil.modul3xml.data

import com.mobil.modul3xml.R

object ProblemRepository {
    private val problemList = listOf(
        CodeforcesProblem(
            problemId = "2220A",
            title = R.string.problem_2220a_title,
            description = R.string.problem_2220a_desc,
            url = "https://codeforces.com/contest/2220/problem/A",
            solutionCode = R.raw.problem_2220a_code, // Updated
            img = R.drawable.a_blocked
        ),
        CodeforcesProblem(
            problemId = "2209B",
            title = R.string.problem_2209b_title,
            description = R.string.problem_2209b_desc,
            url = "https://codeforces.com/contest/2209/problem/B",
            solutionCode = R.raw.problem_2209b_code, // Updated
            img = R.drawable.b_array_operation
        ),
        CodeforcesProblem(
            problemId = "2209A",
            title = R.string.problem_2209a_title,
            description = R.string.problem_2209a_desc,
            url = "https://codeforces.com/contest/2209/problem/A",
            solutionCode = R.raw.problem_2209a_code, // Updated
            img = R.drawable.a_initial_config
        ),
        CodeforcesProblem(
            problemId = "2125B",
            title = R.string.problem_2125b_title,
            description = R.string.problem_2125b_desc,
            url = "https://codeforces.com/contest/2125/problem/B",
            solutionCode = R.raw.problem_2125b_code, // Updated
            img = R.drawable.b_left_and_down
        ),
        CodeforcesProblem(
            problemId = "2209C",
            title = R.string.problem_2209c_title,
            description = R.string.problem_2209c_desc,
            url = "https://codeforces.com/contest/2209/problem/C",
            solutionCode = R.raw.problem_2209c_code, // Updated
            img = R.drawable.c_find_the_zero
        )
    )

    fun getProblemById(id: String): CodeforcesProblem? {
        return problemList.find { it.problemId == id }
    }

    fun getAllProblems(): List<CodeforcesProblem> = problemList
}