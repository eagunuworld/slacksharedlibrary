def call(String buildStatus = 'STARTED') {
 buildStatus = buildStatus ?: 'SUCCESS'

 def color

 if (buildStatus == 'SUCCESS') {
  color = '#47ec05'
  emoji = ':ww:'
 } else if (buildStatus == 'UNSTABLE') {
  color = '#d5ee0d'
  emoji = ':deadpool:'
 } else {
  color = '#ec2805'
  emoji = ':hulk:'
 }
 def msg = "${buildStatus}: `${env.JOB_NAME}` #${env.BUILD_NUMBER}:\n${env.BUILD_URL}"

 def attachments = [
    [
      "color": color,
      "blocks": [
        [
          "type": "header",
          "text": [
            "type": "plain_text",
            "text": "Deployment - ${deploymentName} Pipeline  ${env.emoji}",
            "emoji": true
          ]
        ],
        [
          "type": "section",
          "fields": [
            [
              "type": "mrkdwn",
              "text": "*Job Name:*\n${env.JOB_NAME}"
            ],
            [
              "type": "mrkdwn",
              "text": "*Build Number:*\n${env.BUILD_NUMBER}"
            ]
          ],
          "accessory": [
            "type": "image",
            "image_url": "https://raw.githubusercontent.com/sidd-harth/kubernetes-devops-security/main/slack-emojis/jenkins.png",
            "alt_text": "Slack Icon"
          ]
        ],
        [
          "type": "section",
          "text": [
              "type": "mrkdwn",
              "text": "*Failed Stage Name: * `${env.failedStage}`"
            ],
          "accessory": [
            "type": "button",
            "text": [
              "type": "plain_text",
              "text": "BrowseJenkinsBuild",
              "emoji": true
            ],
            "value": "click_me_123",
            "url": "${env.jenkinsURL}:8080",
            "action_id": "button-action"
          ]
        ],
        [
          "type": "divider"
        ],
        [
          "type": "section",
          "fields": [
            [
              "type": "mrkdwn",
              "text": "*LivePod:*\n${deploymentName}"
            ],
            [
              "type": "mrkdwn",
              "text": "*Node Port*\n30000"
            ]
          ], 
          "accessory": [
            "type": "image",
            "image_url": "https://raw.githubusercontent.com/sidd-harth/kubernetes-devops-security/main/slack-emojis/k8s.png",
            "alt_text": "Kubernetes Icon"
          ]
        ],
        [
          "type": "section",
          "text": [
              "type": "mrkdwn",
              "text": "*Kubernetes Node: * `mss-node01`"
            ],
          "accessory": [
            "type": "button",
            "text": [
              "type": "plain_text",
              "text": "BrowseApplication",
              "emoji": true
            ],
            "value": "click_me_123",
            "url": "${env.serverURL}:30000",
            "action_id": "button-action"
          ]
        ],
        [
          "type": "divider"
        ],
        [
          "type": "section",
          "fields": [
            [
              "type": "mrkdwn",
              "text": "*CommitID:*\n${env.sha_value}"
            ],
            [
              "type": "mrkdwn",
              "text": "*Job_Tag:*\n${env.BUILD_TAG}"
            ]
          ], 
          "accessory": [
            "type": "image",
            "image_url": "https://raw.githubusercontent.com/sidd-harth/kubernetes-devops-security/main/slack-emojis/github.png",
            "alt_text": "Github Icon"
          ]
        ],
        [
          "type": "section",
          "text": [
              "type": "mrkdwn",
              "text": "*Git Branch: * `${GIT_BRANCH}`"
            ],
          "accessory": [
            "type": "button",
            "text": [
              "type": "plain_text",
              "text": "Github Repo URL",
              "emoji": true
            ],
            "value": "click_me_123",
            "url": "${env.GIT_URL}",
            "action_id": "button-action"
          ]
        ]
          ]
        ]
      ]
 
slackSend(iconEmoji: emoji, attachments: attachments)

}
