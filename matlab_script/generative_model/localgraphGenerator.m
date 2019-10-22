function f=localgraphGenerator(n)


clusterSize=n/4;

B11=zeros(clusterSize,clusterSize);
for i=1:clusterSize-1
    for j=i+1:clusterSize
        if rand()<=0.35
            B11(i,j)=1;
            B11(j,i)=B11(i,j);
        end
    end
end

B22=zeros(clusterSize,clusterSize);
for i=1:clusterSize-1
    for j=i+1:clusterSize
        if rand()<=0.35
            B22(i,j)=1;
            B22(j,i)=B22(i,j);
        end
    end
end

B33=zeros(clusterSize,clusterSize);
for i=1:clusterSize-1
    for j=i+1:clusterSize
        if rand()<=0.35
            B33(i,j)=1;
            B33(j,i)=B33(i,j);
        end
    end
end

B44=zeros(clusterSize,clusterSize);
for i=1:clusterSize-1
    for j=i+1:clusterSize
        if rand()<=0.35
            B44(i,j)=1;
            B44(j,i)=B44(i,j);
        end
    end
end

B12=zeros(clusterSize,clusterSize);
for i=1:clusterSize
    for j=1:clusterSize
        r=rand();
        if r<=0.01
            B12(i,j)=1;
        end
    end
end

B13=zeros(clusterSize,clusterSize);
for i=1:clusterSize
    for j=1:clusterSize
        r=rand();
        if r<=0.01
            B13(i,j)=1;
        end
    end
end

B14=zeros(clusterSize,clusterSize);
for i=1:clusterSize
    for j=1:clusterSize
        r=rand();
        if r<=0.01
            B14(i,j)=1;
        end
    end
end

B23=zeros(clusterSize,clusterSize);
for i=1:clusterSize
    for j=1:clusterSize
        r=rand();
        if r<=0.01
            B23(i,j)=1;
        end
    end
end

B24=zeros(clusterSize,clusterSize);
for i=1:clusterSize
    for j=1:clusterSize
        r=rand();
        if r<=0.01
            B24(i,j)=1;
        end
    end
end

B34=zeros(clusterSize,clusterSize);
for i=1:clusterSize
    for j=1:clusterSize
        r=rand();
        if r<=0.01
            B34(i,j)=1;
        end
    end
end

label=zeros(n,1);
for num=1:4
    label((num-1)*clusterSize+1:num*clusterSize,1)=num;
end
    

filename=['C:\Users\Desktop\local clustering\synthetic graph data\varying node numbers\1\' num2str(n) '\label.mat'];
save(filename,'label');

A=[B11,B12,B13,B14;B12',B22,B23,B24;B13',B23',B33,B34;B14',B24',B34',B44];
filename=['C:\Users\Desktop\local clustering\synthetic graph data\varying node numbers\1\' num2str(n) '\A.mat'];
save(filename,'A');
A=sparse(A);

filename=['C:\Users\Desktop\local clustering\synthetic graph data\varying node numbers\1\' num2str(n) '\network.dat'];
savesparse(filename, A);



f=A;