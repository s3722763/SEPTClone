provider "aws" {
  version = "~> 2.23"
  region = "us-east-1"
}

resource "aws_db_instance" "database" {
  engine                 = "mariadb"
  engine_version         = "10.4.8"
  allocated_storage      = 20
  instance_class         = "db.t2.micro"
  username               = "root"
  password               = var.db_password
  name                   = "project"
  storage_type           = "gp2"
  vpc_security_group_ids = [aws_security_group.database_sg.id]
  publicly_accessible    = false
  db_subnet_group_name = aws_db_subnet_group.db_subnet_group.name
  port                   = 3306
  skip_final_snapshot = true
}
resource "aws_security_group" "database_sg" {
  name        = "database_security_group"
  description = "Database Security Group"
  vpc_id      = var.vpc_id

  ingress {
    from_port   = 3306
    protocol    = "tcp"
    to_port     = 3306
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_subnet" "data_az1" {
  vpc_id                  = var.vpc_id
  cidr_block              = "172.20.80.0/22"
  availability_zone       = "us-east-1a"
  map_public_ip_on_launch = true

  tags = {
    Name = "Data AZ1"
  }
}

resource "aws_subnet" "data_az2" {
  vpc_id                  = var.vpc_id
  cidr_block              = "172.20.88.0/22"
  availability_zone       = "us-east-1b"
  map_public_ip_on_launch = true

  tags = {
    Name = "Data AZ2"
  }
}

resource "aws_subnet" "data_az3" {
  vpc_id                  = var.vpc_id
  cidr_block              = "172.20.96.0/22"
  availability_zone       = "us-east-1c"
  map_public_ip_on_launch = true

  tags = {
    Name = "Data AZ3"
  }
}

resource "aws_db_subnet_group" "db_subnet_group" {
  name        = "db_subnet_group"
  description = "Subnet for project database"
  subnet_ids  = [aws_subnet.data_az1.id, aws_subnet.data_az2.id, aws_subnet.data_az3.id, var.subnet_id]
}