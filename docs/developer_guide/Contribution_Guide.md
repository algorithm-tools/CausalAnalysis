# How to contribute
- Submit an issue on GitHub to receive answers to questions
- Answer other people's issue questions
- Discuss the implementation of new features
- Submit bug fixes or feature PRs
- Publish technical articles related to application case practice


- **General process of contribution**:
  - 1. fork to your own repository
  - 2. git clone to your own repository to local
  - 3. new branch: need to develop what Feature or bug processing, on a related issue title to create a new branch, for example (from the main branch to create a branch to fix the bug, issue number is 3): main/bug-3
  - 4. Submit the code: After development, self-test, and submit the code push to the remote end of their own warehouse, submit the code instructions see below.
  - 5. Enter your own warehouse page, submit PR.
  - 6. Community Committer will do CodeReview, may discuss some details with you, no problem will be merged into the appropriate branch after your submission.
  - 7. When your PR is merged, congratulations, become an official contributor of the current project, your information will be displayed on the project home page Contributors position after a period of time.



# Automatically synchronize the master repository
> Automatically synchronize the code from the main repository (upstream) to your repository (origin) to keep your repository code synchronized with the main repository code.

**Steps**
- Fork the main repository to your GitHub account.
- Click on the repository you want to fork, and click on the Actions tab.
- You will be prompted for some information, click the `I understand my workflows, go ahead and enable them` button.
- Once this is done, the synchronization will be performed at 00:00:00 every day. If the workflow file is not changed, the synchronization will continue to be performed automatically according to the timer configuration.


# Unsolicited Pickup of Issue or Feature
If you want to implement a Feature or fix a bug. please refer to the following:
- First fork ad4j to your personal repository.
- All discovered bugs and new Features are recommended to be managed using the Issues Page.
- If you want to develop and implement a Feature, please reply to the Issue associated with the Feature, indicating that you are currently working on it. If you want to develop a Feature feature, reply to the Issue it is associated with to show that you are currently working on the Issue, and when you do, set a deadline for yourself and add it to the reply.
- You should start your work by creating a new branch, the name of which you should refer to the Pull Request. For example, if you want to complete the feature and submit an Issue demo, your branch name should be feature-demo.
- When you're done, send a Pull Request to ad4j's main branch. See below for submission PRs.



# Submit Issue

Title format: [Issue type][`module name`] Issue Description
Where Issue type is as follows:



  | Issue type | Desc                                                          | Case                                                                     |
|------------|---------------------------------------------------------------|--------------------------------------------------------------------------|
  | Feature    | Includes desired new features and functionality               | [Feature][adm] Add xxx                                                   |
  | Bug        | Bugs in the program                                           | [Bug][adm] gesd exception when some env                                  |
  | Improve    | Improvements, not limited to code format, program performance | [Improve][adm] 2ndDerivationMBP Filter without significant fluctuations. |
  | Test       | Specific to the test case section                             | [Test][adm] Add 2ndDerivationMBP test                                    |
  | Doc        | doc desc                                                      | [Doc][doc] update xxx.                                                   |

- Module name can be filled with the name of the package mainly affected, or * if it mainly affects most packages or more packages.
- If it is a Bug Issue, then you need to describe the bug in detail: the problem encountered, the steps to reproduce it (generously post screenshots), the version, your considerations and suggestions.
- If it is Feature class Issue, you need to describe the function in detail: detailed description of the function, usage, scope of impact, implementation plan and other key information.


# Submit code
Submission Instructions: [Submission Type] [Impact Module] Content

See the [Submit Issue] section for types and impact modules.
> Suggested submission type spliced with Issue number e.g.: feature-#2

# Submit PR 
- Submission Title Format: [Submission Type - Issue Number] [Impact Module] Topic

    See the [Submission Issue] section for types and impact modules. The Impact Module is not required.

- Submission Content Format:
  - Itemized statement of changes
  - A single line associated with specific Issues (can be more than one), e.g. for Issue #16: Issue #16
  - Statements do not need to end with a period

- Case：
```text
[Doc-001][doc] add commit message

- commit message RIP
- build some conventions
- help the commit messages become clean and tidy
- help developers and release managers better track issues and clarify the optimization in the version iteration

- Issues: Issues #001
```
