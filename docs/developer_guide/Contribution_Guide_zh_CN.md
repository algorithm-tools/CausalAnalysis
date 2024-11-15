# 如何贡献
- 通过github提交issue获得问题解答
- 回答别人的issue问题
- 讨论新Feature的实现
- 提交bugfix或者feature的PR
- 发表应用案例实践相关技术文章


- **贡献的一般流程**：
  - 1.fork 到自己仓库
  - 2.把自己仓库git clone到本地
  - 3.新建分支：需要开发什么Feature或bug处理，就以相关issue标题创建新分支，例如（从main分支上创建一个修复bug的分支，issue编号是3）：main/bug-3
  - 4.提交代码：开发完后，进行自测，并提交代码push到自己仓库远端，提交代码说明见下文。
  - 5.进入自己仓库页面，提交PR。
  - 6.社区Committer们会做CodeReview，可能会跟你讨论一些细节，没什么问题后会将你的提交合并到相应分支。
  - 7.当你的PR被Merge后，恭喜你，成为当前项目的一名官方Contributor，你的信息会在一段时间后显示在项目主页Contributors位置。


# 自动同步主仓库
> 自动同步主仓库 (upstream) 的代码到你的仓库 (origin)，保持你的仓库代码与主仓库代码同步。

**步骤**
- Fork 主仓库到你的 GitHub 账号下.
- 点击 Fork 之后的仓库, 点击 Actions 标签.
- 会提示一些介绍, 点击 `I understand my workflows, go ahead and enable them` 按钮.
- 完成之后,会在每天的 00:00:00 执行同步操作. 后续该 workflow 文件不改变的情况下,会一直按照定时配置自动执行同步操作.

# 主动领取Issue或Feature
如果你想实现某个 Feature 或者修复某个 Bug。请参考以下内容：
- 首先fork ad4j到你的个人仓库。
- 所有发现的Bug 与新 Feature 建议使用 Issues Page 进行管理。
- 如果想要开发实现某个 Feature 功能，请先回复该功能所关联的 Issue，表明你当前正在这个 Issue 上工作。 并在回复的时候为自己设置一个最后期限，并添加到回复内容中。
- 你应该新建一个分支来开始你的工作，分支的名字参考 Pull Request 需知。比如，你想完成 feature 功能并提交了 Issue demo，那么你的 branch 名字应为 feature-demo。
- 完成后，发送一个 Pull Request 到 ad4j 的 main 分支。提交PR参考下方内容。


# 提交Issue

标题格式：[Issue 类型][`模块名`] Issue 描述
其中Issue 类型如下：



  | Issue type | Desc            | Case                                                                     |
|------------|-----------------|--------------------------------------------------------------------------|
  | Feature    | 包含所期望的新功能和新特性   | [Feature][adm] Add xxx                                                   |
  | Bug        | 程序中存在的Bug       | [Bug][adm] gesd exception when some env                                  |
  | Improve    | 改进，不限于代码格式，程序性能 | [Improve][adm] 2ndDerivationMBP Filter without significant fluctuations. |
  | Test       | 专门针对测试用例部分      | [Test][adm] Add 2ndDerivationMBP test                                    |
  | Doc        | 文档部分            | [Doc][doc] update xxx.                                                   |
 
- 模块名可以填主要影响的包名，如果主要影响大部分包或较多包，可以填 *
- 如果是Bug Issue，那么需要描述bug详细：遇到的问题、复现步骤（大方的贴上截图）、版本、你的考虑和建议。
- 如果是Feature类的Issue，需要详细描述功能：功能详细描述、用途、影响范围、实现方案等关键信息。


# 提交代码
提交说明：[提交类型][影响模块] 内容

类型和影响模块参见【提交Issue】部分。
> 建议提交类型拼接Issue编号例如：feature-#2

# 提交PR
- 提交标题格式：[提交类型-Issue编号][影响模块] 主题

类型和影响模块参见【提交Issue】部分。影响模块非必须。

- 提交内容格式：
  - 逐项陈述改动的内容
  - 单独一行关联具体Issues（可以多个），例如关联编号为#16的Issue： Issue #16
  - 语句不需要句号结尾

- 举个例子：
```text
[Doc-001][doc] add commit message

- commit message RIP
- build some conventions
- help the commit messages become clean and tidy
- help developers and release managers better track issues and clarify the optimization in the version iteration

- Issues: Issues #001
```
