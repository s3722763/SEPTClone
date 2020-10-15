provider "aws" {
  version = "~> 2.23"
  region = "us-east-1"
}

resource "aws_s3_bucket" "project_kops_state" {
  bucket = "project-kops-state"
  acl = "private"
  force_destroy = true

  versioning {
    enabled = true
  }

  tags = {
    Name = "Projects kops remote state"
  }
}

output "kops_state_bucket_name" {
  value = aws_s3_bucket.project_kops_state.bucket
}