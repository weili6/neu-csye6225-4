#!/bin/bash
# Launch 1 EC2 t2.micro instance and configure route53

#ubuntu16.04
IMAGE="ami-6edd3078"
SECURITY_GROUP="sg-09c7a875"
RECORD_SET="ec2.neu-csye6225-spring2017-team-4.me."

INSTANCE=$(
    aws ec2 run-instances \
        --image-id "$IMAGE" \
        --count 1 \
        --instance-type t2.micro \
        --disable-api-termination \
        --subnet-id subnet-3969e205 \
        --security-group-ids "$SECURITY_GROUP" \
        --key-name husky \
        --associate-public-ip-address \
        --block-device-mappings '[{"DeviceName":"/dev/sda1","Ebs":{"VolumeSize":10,"DeleteOnTermination":true,"VolumeType":"standard"}}]')

ID=$(echo $INSTANCE | jq -r '.Instances[0].InstanceId')
sleep 30
while ! $(aws ec2 describe-instances --instance-ids "$ID" | grep -q running); do
    sleep 10
done

IP=$(aws ec2 describe-instances --instance-ids "$ID" | jq -r '.Reservations[0].Instances[0].PublicIpAddress')

read -rd '' ROUTE_CONFIG <<EOF
{
    "HostedZoneId": "Z27PGMTGJ30BYT",
    "ChangeBatch": {
        "Changes": [
            {
                "Action": "CREATE",
                "ResourceRecordSet": {
                    "Name": "$RECORD_SET",
                    "Type": "A",
                    "TTL": 300,
                    "ResourceRecords": [
                        {
                            "Value": "$IP"
                        }
                    ]
                }
            }
        ]
    }
}
EOF

aws route53 change-resource-record-sets --cli-input-json "$ROUTE_CONFIG"

# login
# ssh -i .ssh/husky.pem ubuntu@"$IP"
