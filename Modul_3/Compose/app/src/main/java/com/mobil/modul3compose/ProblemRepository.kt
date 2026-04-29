package com.mobil.modul3compose

object ProblemRepository {
    val problemList = listOf(
        CodeforcesProblem(
            problemId = "2220A",
            title = "Blocked",
            description = "Determine if the array elements can be rearranged so that no identical subsets can be formed in the prefix.",
            tags = listOf("greedy", "constructive algorithms"),
            url = "https://codeforces.com/contest/2220/problem/A",
            solutionCode = "#include <bits/stdc++.h>\n" +
                    "//For ordered_set\n" +
                    "#include <ext/pb_ds/assoc_container.hpp>\n" +
                    "#include <ext/pb_ds/tree_policy.hpp>\n" +
                    "#define MOD 1000000007\n" +
                    "#define test int t; cin>>t; while(t--)\n" +
                    "#define init(arr,val) memset(arr,val,sizeof(arr))\n" +
                    "#define loop(i,a,b) for(int i=a;i<b;i++)\n" +
                    "#define loopr(i,a,b) for(int i=a;i>=b;i--)\n" +
                    "#define loops(i,a,b,step) for(int i=a;i<b;i+=step)\n" +
                    "#define looprs(i,a,b,step) for(int i=a;i>=b;i-=step)\n" +
                    "#define ull unsigned long long int\n" +
                    "#define ll long long int\n" +
                    "#define P pair\n" +
                    "#define PLL pair<long long, long long>\n" +
                    "#define PII pair<int, int>\n" +
                    "#define PUU pair<unsigned long long int, unsigned long long int>\n" +
                    "#define L list\n" +
                    "#define V vector\n" +
                    "#define D deque\n" +
                    "#define ST set\n" +
                    "#define MS multiset\n" +
                    "#define M map\n" +
                    "#define UM unordered_map\n" +
                    "#define mp make_pair\n" +
                    "#define pb push_back\n" +
                    "#define pf push_front\n" +
                    "#define MM multimap\n" +
                    "#define F first\n" +
                    "#define S second\n" +
                    "#define IT iterator\n" +
                    "#define RIT reverse_iterator\n" +
                    "#define FAST ios_base::sync_with_stdio(false);cin.tie();cout.tie();\n" +
                    "#define FILE_READ_IN freopen(\"input.txt\",\"r\",stdin);\n" +
                    "#define FILE_READ_OUT freopen(\"output.txt\",\"w\",stdout);\n" +
                    "#define all(a) a.begin(),a.end()\n" +
                    "using namespace std;\n" +
                    "// For ordered_set\n" +
                    "using namespace __gnu_pbds;\n" +
                    "template <typename T>\n" +
                    "using ord_set = tree<T,null_type,less<T>,rb_tree_tag,tree_order_statistics_node_update>;\n" +
                    "const ll maxn = 1e5;\n" +
                    "const ll inf = 1e9;\n" +
                    "const double pi = acos(-1);\n" +
                    "\n" +
                    "void solve(){\n" +
                    "    int n;\n" +
                    "    cin >> n;\n" +
                    "    V<int> a(n);\n" +
                    "    M<int, int> m;\n" +
                    "    bool is_blocked = false;\n" +
                    "    loop(i,0,n) {\n" +
                    "        cin >> a[i];\n" +
                    "        m[a[i]]++;\n" +
                    "        if(m[a[i]] > 1) {\n" +
                    "            is_blocked = true;\n" +
                    "        }\n" +
                    "    }\n" +
                    "\n" +
                    "    if(is_blocked) {\n" +
                    "        cout << \"-1\\n\";\n" +
                    "    } else {\n" +
                    "        sort(all(a), greater<int>());\n" +
                    "        loop(i,0,n) {\n" +
                    "            cout << a[i] << \" \";\n" +
                    "        }        \n" +
                    "        cout << \"\\n\";\n" +
                    "    }\n" +
                    "\n" +
                    "\n" +
                    "}\n" +
                    "\n" +
                    "int main(){\n" +
                    "   int t = 0;\n" +
                    "   cin >> t;\n" +
                    "   while(t--){\n" +
                    "       solve();\n" +
                    "   }\n" +
                    "   return 0;\n" +
                    "}",
            img = R.drawable.a_blocked
        ),
        CodeforcesProblem(
            problemId = "2209B",
            title = "Array Operations",
            description = "Process the sequence to find the optimal arrangement based on the given continuous interval conditions.",
            tags = listOf("implementation", "math"),
            url = "https://codeforces.com/contest/2209/problem/B",
            solutionCode = "#include <bits/stdc++.h>\n" +
                    "//For ordered_set\n" +
                    "#include <ext/pb_ds/assoc_container.hpp>\n" +
                    "#include <ext/pb_ds/tree_policy.hpp>\n" +
                    "#define MOD 1000000007\n" +
                    "#define test int t; cin>>t; while(t--)\n" +
                    "#define init(arr,val) memset(arr,val,sizeof(arr))\n" +
                    "#define loop(i,a,b) for(int i=a;i<b;i++)\n" +
                    "#define loopr(i,a,b) for(int i=a;i>=b;i--)\n" +
                    "#define loops(i,a,b,step) for(int i=a;i<b;i+=step)\n" +
                    "#define looprs(i,a,b,step) for(int i=a;i>=b;i-=step)\n" +
                    "#define ull unsigned long long int\n" +
                    "#define ll long long int\n" +
                    "#define P pair\n" +
                    "#define PLL pair<long long, long long>\n" +
                    "#define PII pair<int, int>\n" +
                    "#define PUU pair<unsigned long long int, unsigned long long int>\n" +
                    "#define L list\n" +
                    "#define V vector\n" +
                    "#define D deque\n" +
                    "#define ST set\n" +
                    "#define MS multiset\n" +
                    "#define M map\n" +
                    "#define UM unordered_map\n" +
                    "#define mp make_pair\n" +
                    "#define pb push_back\n" +
                    "#define pf push_front\n" +
                    "#define MM multimap\n" +
                    "#define F first\n" +
                    "#define S second\n" +
                    "#define IT iterator\n" +
                    "#define RIT reverse_iterator\n" +
                    "#define FAST ios_base::sync_with_stdio(false);cin.tie();cout.tie();\n" +
                    "#define FILE_READ_IN freopen(\"input.txt\",\"r\",stdin);\n" +
                    "#define FILE_READ_OUT freopen(\"output.txt\",\"w\",stdout);\n" +
                    "#define all(a) a.begin(),a.end()\n" +
                    "using namespace std;\n" +
                    "// For ordered_set\n" +
                    "using namespace __gnu_pbds;\n" +
                    "template <typename T>\n" +
                    "using ord_set = tree<T,null_type,less<T>,rb_tree_tag,tree_order_statistics_node_update>;\n" +
                    "const ll maxn = 1e5;\n" +
                    "const ll inf = 1e9;\n" +
                    "const double pi = acos(-1);\n" +
                    "\n" +
                    "void solve(){\n" +
                    "    int n;\n" +
                    "    cin >> n;\n" +
                    "    V<ll> a(n);\n" +
                    "    loop(i, 0, n) {\n" +
                    "        cin >> a[i];\n" +
                    "    }\n" +
                    "\n" +
                    "    V<int> ans(n);\n" +
                    "    loop(i, 0, n) {\n" +
                    "        int mi = 0, ma = 0;\n" +
                    "        loop(j, i, n) {\n" +
                    "            if(a[j] < a[i]) {\n" +
                    "                mi++;\n" +
                    "            }\n" +
                    "            else if(a[j] > a[i]) {\n" +
                    "                ma++;\n" +
                    "            }\n" +
                    "        }\n" +
                    "        ans[i] = max(mi, ma);\n" +
                    "    }\n" +
                    "\n" +
                    "    loop(i, 0, n){\n" +
                    "        cout << ans[i] << \" \";\n" +
                    "    }cout << '\\n';\n" +
                    "}\n" +
                    "\n" +
                    "int main(){\n" +
                    "   int t = 0;\n" +
                    "   cin >> t;\n" +
                    "   while(t--){\n" +
                    "       solve();\n" +
                    "   }\n" +
                    "   return 0;\n" +
                    "}",
            img = R.drawable.b_array_operation
        ),
        CodeforcesProblem(
            problemId = "2209A",
            title = "Initial Configuration",
            description = "Find the minimum number of operations required to achieve the valid array state.",
            tags = listOf("greedy", "sortings"),
            url = "https://codeforces.com/contest/2209/problem/A",
            solutionCode = "#include <bits/stdc++.h>\n" +
                    "//For ordered_set\n" +
                    "#include <ext/pb_ds/assoc_container.hpp>\n" +
                    "#include <ext/pb_ds/tree_policy.hpp>\n" +
                    "#define MOD 1000000007\n" +
                    "#define test int t; cin>>t; while(t--)\n" +
                    "#define init(arr,val) memset(arr,val,sizeof(arr))\n" +
                    "#define loop(i,a,b) for(int i=a;i<b;i++)\n" +
                    "#define loopr(i,a,b) for(int i=a;i>=b;i--)\n" +
                    "#define loops(i,a,b,step) for(int i=a;i<b;i+=step)\n" +
                    "#define looprs(i,a,b,step) for(int i=a;i>=b;i-=step)\n" +
                    "#define ull unsigned long long int\n" +
                    "#define ll long long int\n" +
                    "#define P pair\n" +
                    "#define PLL pair<long long, long long>\n" +
                    "#define PII pair<int, int>\n" +
                    "#define PUU pair<unsigned long long int, unsigned long long int>\n" +
                    "#define L list\n" +
                    "#define V vector\n" +
                    "#define D deque\n" +
                    "#define ST set\n" +
                    "#define MS multiset\n" +
                    "#define M map\n" +
                    "#define UM unordered_map\n" +
                    "#define mp make_pair\n" +
                    "#define pb push_back\n" +
                    "#define pf push_front\n" +
                    "#define MM multimap\n" +
                    "#define F first\n" +
                    "#define S second\n" +
                    "#define IT iterator\n" +
                    "#define RIT reverse_iterator\n" +
                    "#define FAST ios_base::sync_with_stdio(false);cin.tie();cout.tie();\n" +
                    "#define FILE_READ_IN freopen(\"input.txt\",\"r\",stdin);\n" +
                    "#define FILE_READ_OUT freopen(\"output.txt\",\"w\",stdout);\n" +
                    "#define all(a) a.begin(),a.end()\n" +
                    "using namespace std;\n" +
                    "// For ordered_set\n" +
                    "using namespace __gnu_pbds;\n" +
                    "template <typename T>\n" +
                    "using ord_set = tree<T,null_type,less<T>,rb_tree_tag,tree_order_statistics_node_update>;\n" +
                    "const ll maxn = 1e5;\n" +
                    "const ll inf = 1e9;\n" +
                    "const double pi = acos(-1);\n" +
                    "\n" +
                    "void solve(){\n" +
                    "    ll n, c, k;\n" +
                    "    cin >> n >> c >> k;\n" +
                    "    V<int> a(n);\n" +
                    "    loop(i, 0, n) {\n" +
                    "        cin >> a[i];\n" +
                    "    }\n" +
                    "    \n" +
                    "    sort(all(a));\n" +
                    "\n" +
                    "    loop(i, 0, n) {\n" +
                    "        if(c < a[i]) {\n" +
                    "            break;\n" +
                    "        }\n" +
                    "\n" +
                    "        if(k <= 0) {\n" +
                    "            c += a[i];\n" +
                    "        }else{\n" +
                    "            ll diff = abs(a[i] - c);\n" +
                    "            if(diff > k) {\n" +
                    "                c += a[i] + k;\n" +
                    "                k -= k;\n" +
                    "            }else{\n" +
                    "                c += a[i] + diff;\n" +
                    "                k -= diff;\n" +
                    "            }\n" +
                    "            \n" +
                    "        }\n" +
                    "        \n" +
                    "\n" +
                    "    }\n" +
                    "    \n" +
                    "    cout << c << endl;\n" +
                    "}\n" +
                    "\n" +
                    "int main(){\n" +
                    "   int t = 0;\n" +
                    "   cin >> t;\n" +
                    "   while(t--){\n" +
                    "       solve();\n" +
                    "   }\n" +
                    "   return 0;\n" +
                    "}",
            img = R.drawable.a_initial_config
        ),
        CodeforcesProblem(
            problemId = "2125B",
            title = "Left and Down",
            description = "Navigate a 2D coordinate system using only 'Left' and 'Down' moves to calculate the required combinations.",
            tags = listOf("dp", "geometry"),
            url = "https://codeforces.com/contest/2125/problem/B",
            solutionCode = "#include<bits/stdc++.h>\n" +
                    "#include <ext/pb_ds/assoc_container.hpp>\n" +
                    "using namespace __gnu_pbds;\n" +
                    "using namespace std;\n" +
                    "\n" +
                    "#define ff                first\n" +
                    "#define ss                second\n" +
                    "#define int               long long\n" +
                    "#define pb                push_back\n" +
                    "#define mp                make_pair\n" +
                    "#define pii               pair<int,int>\n" +
                    "#define vi                vector<int>\n" +
                    "#define vii               vector<vi>\n" +
                    "#define viii              vector<vii>\n" +
                    "#define mii               map<int,int>\n" +
                    "#define pqb               priority_queue<int>\n" +
                    "#define pqs               priority_queue<int, vi, greater<int>>\n" +
                    "#define setbits(x)        __builtin_popcountll(x)\n" +
                    "#define zrobits(x)        __builtin_ctzll(x)\n" +
                    "#define all(x)            (x).begin(), (x).end()\n" +
                    "\n" +
                    "#define MOD               1000000007\n" +
                    "#define INF               1e18\n" +
                    "#define EPS               1e-9\n" +
                    "#define DEG_TO_RAD        0.0174532925199432957692\n" +
                    "#define RAD_TO_DEG        57.2957795130823208768\n" +
                    "#define PI                3.14159265358979323846\n" +
                    "#define E                 2.71828182845904523536\n" +
                    "\n" +
                    "#define ps(x,y)           fixed << setprecision(y) << x\n" +
                    "#define mk(arr,n,type)    type *arr = new type[n];\n" +
                    "#define w(x)              int x; cin >> x; while (x--)\n" +
                    "mt19937                   rng(chrono::steady_clock::now().time_since_epoch().count());\n" +
                    "\n" +
                    "#define FOR(i, a, b)      for (int i = (a); i < (b); ++i)\n" +
                    "#define REP(i, n)         FOR(i, 0, n)\n" +
                    "#define FORD(i, a, b)     for (int i = (a); i >= (b); --i)\n" +
                    "#define REPD(i, n)        FORD(i, n - 1, 0)\n" +
                    "\n" +
                    "typedef tree<int, null_type, less<int>, rb_tree_tag, tree_order_statistics_node_update> pbds;\n" +
                    "\n" +
                    "void c_p_c(){\n" +
                    "#ifndef ONLINE_JUDGE\n" +
                    "    freopen(\"input.txt\", \"r\", stdin);\n" +
                    "    freopen(\"output.txt\", \"w\", stdout);\n" +
                    "#endif\n" +
                    "}\n" +
                    "\n" +
                    "int32_t main(){\n" +
                    "    //c_p_c();\n" +
                    "    ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);\n" +
                    "\n" +
                    "    int T; cin >> T;\n" +
                    "\n" +
                    "    REP(i, T){\n" +
                    "        vector<pair<int, int>> used;\n" +
                    "        int a, b, k;\n" +
                    "        cin >> a >> b >> k;\n" +
                    "\n" +
                    "        if(a == b){\n" +
                    "            cout << 1 << '\\n';\n" +
                    "            continue;\n" +
                    "        }\n" +
                    "        if(a <= k && b <= k){\n" +
                    "            cout << 1 << '\\n';\n" +
                    "            continue;\n" +
                    "        }\n" +
                    "\n" +
                    "        int gc = gcd(a, b);\n" +
                    "        if(gc != 1 && a/gc <= k && b/gc <= k){\n" +
                    "            cout << 1 << '\\n';\n" +
                    "        }else{\n" +
                    "            cout << 2 << '\\n';\n" +
                    "        }\n" +
                    "    }\n" +
                    "\n" +
                    "    return 0;\n" +
                    "};",
            img = R.drawable.b_left_and_down
        ),
        CodeforcesProblem(
            problemId = "2209C",
            title = "Find the Zero",
            description = "Interactive problem: You are given a hidden array of length 2n containing 1 to n and n zeros. Query pairs to find the positions of the zeroes.",
            tags = listOf("interactive", "constructive algorithms"),
            url = "https://codeforces.com/contest/2209/problem/C",
            solutionCode = "#include <bits/stdc++.h>\n" +
                    "//For ordered_set\n" +
                    "#include <ext/pb_ds/assoc_container.hpp>\n" +
                    "#include <ext/pb_ds/tree_policy.hpp>\n" +
                    "#define MOD 1000000007\n" +
                    "#define test int t; cin>>t; while(t--)\n" +
                    "#define init(arr,val) memset(arr,val,sizeof(arr))\n" +
                    "#define loop(i,a,b) for(int i=a;i<b;i++)\n" +
                    "#define loopr(i,a,b) for(int i=a;i>=b;i--)\n" +
                    "#define loops(i,a,b,step) for(int i=a;i<b;i+=step)\n" +
                    "#define looprs(i,a,b,step) for(int i=a;i>=b;i-=step)\n" +
                    "#define ull unsigned long long int\n" +
                    "#define ll long long int\n" +
                    "#define P pair\n" +
                    "#define PLL pair<long long, long long>\n" +
                    "#define PII pair<int, int>\n" +
                    "#define PUU pair<unsigned long long int, unsigned long long int>\n" +
                    "#define L list\n" +
                    "#define V vector\n" +
                    "#define D deque\n" +
                    "#define ST set\n" +
                    "#define MS multiset\n" +
                    "#define M map\n" +
                    "#define UM unordered_map\n" +
                    "#define mp make_pair\n" +
                    "#define pb push_back\n" +
                    "#define pf push_front\n" +
                    "#define MM multimap\n" +
                    "#define F first\n" +
                    "#define S second\n" +
                    "#define IT iterator\n" +
                    "#define RIT reverse_iterator\n" +
                    "#define FAST ios_base::sync_with_stdio(false);cin.tie();cout.tie();\n" +
                    "#define FILE_READ_IN freopen(\"input.txt\",\"r\",stdin);\n" +
                    "#define FILE_READ_OUT freopen(\"output.txt\",\"w\",stdout);\n" +
                    "#define all(a) a.begin(),a.end()\n" +
                    "using namespace std;\n" +
                    "// For ordered_set\n" +
                    "using namespace __gnu_pbds;\n" +
                    "template <typename T>\n" +
                    "using ord_set = tree<T,null_type,less<T>,rb_tree_tag,tree_order_statistics_node_update>;\n" +
                    "const ll maxn = 1e5;\n" +
                    "const ll inf = 1e9;\n" +
                    "const double pi = acos(-1);\n" +
                    "\n" +
                    "void solve(){\n" +
                    "    int n;\n" +
                    "    cin >> n;\n" +
                    "\n" +
                    "    int ans = -1;\n" +
                    "    loop(i, 1, n){\n" +
                    "        cout << \"? \" << 2*i+1 << \" \" << 2*i+2 << endl;\n" +
                    "        cout.flush();\n" +
                    "\n" +
                    "        int in; cin >> in;\n" +
                    "        if(in == 1) {\n" +
                    "            ans = 2*i+1;\n" +
                    "            break;\n" +
                    "        }\n" +
                    "    }\n" +
                    "\n" +
                    "    if(ans == -1){\n" +
                    "        cout << \"? \" << 1 << \" \" << 3 << endl;\n" +
                    "        cout.flush();\n" +
                    "        int in; cin >> in;\n" +
                    "        if(in == 1){   \n" +
                    "            ans = 1;\n" +
                    "        }else{\n" +
                    "            cout << \"? \" << 1 << \" \" << 4 << endl;\n" +
                    "            cout.flush();\n" +
                    "            cin >> in;\n" +
                    "            if(in == 1){\n" +
                    "                ans = 4;\n" +
                    "            }else{\n" +
                    "                ans = 2;\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }\n" +
                    "\n" +
                    "    cout << \"! \" << ans << endl;\n" +
                    "    cout.flush();\n" +
                    "}\n" +
                    "\n" +
                    "int main(){\n" +
                    "   int t = 0;\n" +
                    "   cin >> t;\n" +
                    "   while(t--){\n" +
                    "       solve();\n" +
                    "   }\n" +
                    "   return 0;\n" +
                    "}",
            img = R.drawable.c_find_the_zero
        )
    )

    fun getProblemById(id: String): CodeforcesProblem? {
        return problemList.find { it.problemId == id }
    }

    fun getAllProblems(): List<CodeforcesProblem> = problemList
}